package com.sb.Resume.Analyzer.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;


@Service
public class ClaudeService {

        private final ChatClient chatClient;

        public ClaudeService(ChatClient chatClient) {
                this.chatClient = chatClient;
        }

        public <T> T analyzeResume(PromptTemplate userPrompt, SystemPromptTemplate systemPrompt, Class<T> responseType) {
                return chatClient.prompt()
                                .user(userPrompt.getTemplate())
                                .system(systemPrompt.getTemplate())
                                .call()
                                .entity(responseType);
        }
}