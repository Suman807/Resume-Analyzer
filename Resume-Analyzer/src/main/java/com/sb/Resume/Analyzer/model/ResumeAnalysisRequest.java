package com.sb.Resume.Analyzer.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeAnalysisRequest {

    private MultipartFile resume;
    private String jobDescription;
}
