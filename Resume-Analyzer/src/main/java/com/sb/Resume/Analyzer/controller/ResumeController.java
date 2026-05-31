package com.sb.Resume.Analyzer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sb.Resume.Analyzer.model.ResumeAnalysisRequest;
import com.sb.Resume.Analyzer.service.PdfExtractionService;
import com.sb.Resume.Analyzer.service.ResumeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    private final PdfExtractionService pdfExtractionService;

    @PostMapping("/extract")
    public ResponseEntity<?> extractTextFromPdf(
            @RequestParam("file") MultipartFile file) {
        try {
            String extractedText = pdfExtractionService.extractText(file);
            return ResponseEntity.status(HttpStatus.OK).body(extractedText);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error extracting text from PDF: " + e.getMessage());
        }
    }

    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> analyze(
            @ModelAttribute ResumeAnalysisRequest request)
            throws Exception {

        try {
            String extractedText = pdfExtractionService.extractText(request.getResume());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(resumeService.analyze(extractedText, request.getJobDescription()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error analyzing resume: " + e.getMessage());
        }
    }
}