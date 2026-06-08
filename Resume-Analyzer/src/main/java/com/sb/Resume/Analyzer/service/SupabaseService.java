package com.sb.Resume.Analyzer.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SupabaseService {

    private final JdbcTemplate jdbcTemplate;

    public SupabaseService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean testConnection() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        return Objects.equals(result, 1);
    }

    public String getDatabaseVersion() {
        return jdbcTemplate.queryForObject("SELECT version()", String.class);
    }

    public List<String> listTables() {
        return jdbcTemplate.query(
                "SELECT tablename FROM pg_tables WHERE schemaname NOT IN ('pg_catalog','information_schema') ORDER BY tablename",
                (rs, rowNum) -> rs.getString(1));
    }

    public List<Map<String, Object>> executeSql(String sql) {
        return jdbcTemplate.queryForList(sql);
    }
}
