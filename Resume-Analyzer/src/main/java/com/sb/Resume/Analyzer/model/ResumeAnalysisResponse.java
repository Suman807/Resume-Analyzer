package com.sb.Resume.Analyzer.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeAnalysisResponse {

    private Integer atsScore;

    private List<String> strengths;

    private List<String> weaknesses;

    private List<String> missingKeywords;

    private List<String> suggestions;

    private String summary;
}
