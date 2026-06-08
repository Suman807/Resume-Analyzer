package com.sb.Resume.Analyzer.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sb.Resume.Analyzer.advisor.CustomAdvisorConfiguration;

@Configuration
public class ChatClientConfiguration {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
            .defaultAdvisors(new CustomAdvisorConfiguration())
            .build();
    }
}
