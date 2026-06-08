package com.sb.Resume.Analyzer.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SupabaseServiceTest {

    @Autowired
    private SupabaseService supabaseService;

    @Test
    void testSupabaseConnection() {
        assertTrue(supabaseService.testConnection(), "Should be able to connect to Supabase/Postgres");
    }
}
