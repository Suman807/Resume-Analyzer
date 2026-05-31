package com.sb.Resume.Analyzer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

@Configuration
public class ClaudeConfiguration {

    @Bean
    public AnthropicClient anthropicClient(){
        return AnthropicOkHttpClient.fromEnv();
    }
}
