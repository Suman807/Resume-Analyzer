package com.sb.Resume.Analyzer.service;

import org.springframework.stereotype.Service;

import com.anthropic.client.AnthropicClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClaudeService {

    private final AnthropicClient anthropicClient;

    public String analyzeResume(
            String resume,
            String jobDescription) {

        String prompt = """
                You are an ATS Resume Analyzer.

                IMPORTANT:
                Return ONLY raw JSON.
                Do NOT wrap the response in markdown.
                Do NOT use ```json.
                Do NOT add explanations.

                Schema:

                {
                  "atsScore": 0,
                  "strengths": [],
                  "weaknesses": [],
                  "missingKeywords": [],
                  "suggestions": [],
                  "summary": ""
                }

                Resume:
                %s

                Job Description:
                %s
                """.formatted(resume, jobDescription);

        // Claude API call here
        MessageCreateParams params = MessageCreateParams.builder()
                .model("claude-sonnet-4-20250514")
                .maxTokens(2000)
                .addUserMessage(prompt)
                .build();

        Message message = anthropicClient.messages().create(params);

        String response = message.content().get(0).text().get().text();

        // if the response is wrapped in markdown, remove the markdown syntax
        response = response
                .replaceAll("^```json\\s*", "")
                .replaceAll("^```\\s*", "")
                .replaceAll("\\s*```$", "")
                .trim();

        System.out.println("Claude response: " + response);

        return response;
    }
}