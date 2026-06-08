package com.sb.Resume.Analyzer.service;

import java.util.Map;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeService {

        @Value("classpath:prompts/system_prompt.st")
        private Resource systemPrompt;

        @Value("classpath:prompts/user_prompt.st")
        private Resource userPrompt;

        private final ClaudeService claudeService;

        public <T> T analyze(String resume, String jobDescription, Class<T> responseType) {
                SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate.builder().resource(systemPrompt).build();
                PromptTemplate userPromptTemplate = PromptTemplate.builder().resource(userPrompt).build();
                String userPrompt = userPromptTemplate.render(
                        Map.of(
                                "resume", resume, 
                                "jobDescription", jobDescription
                        )
                );

                return claudeService.analyzeResume(PromptTemplate.builder().template(userPrompt).build(), systemPromptTemplate, responseType);
        }
}