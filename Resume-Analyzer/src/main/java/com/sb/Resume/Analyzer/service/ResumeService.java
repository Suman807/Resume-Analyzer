package com.sb.Resume.Analyzer.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.Resume.Analyzer.model.ResumeAnalysisResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeService {

        private final ClaudeService claudeService;

        private final ObjectMapper objectMapper;

        public ResumeAnalysisResponse analyze(
                        String resumeText,
                        String jd)
                        throws JsonProcessingException {

                String json = claudeService.analyzeResume(
                                resumeText,
                                jd);

                return objectMapper.readValue(
                                json,
                                ResumeAnalysisResponse.class);
        }
}