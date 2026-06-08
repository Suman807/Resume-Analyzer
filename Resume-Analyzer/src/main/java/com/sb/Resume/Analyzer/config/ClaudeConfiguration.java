// package com.sb.Resume.Analyzer.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.anthropic.client.AnthropicClient;

// @Configuration
// public class ClaudeConfiguration {

// @Value("${spring.ai.anthropic.api-key}")
// private String anthropicApiKey;

// @Bean
// public AnthropicClient anthropicClient() {
// return AnthropicClient.builder()
// .apiKey(anthropicApiKey)
// .build();
// }
// }
