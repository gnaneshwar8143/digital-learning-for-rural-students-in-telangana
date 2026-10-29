package com.telangana.digitallearning.controller;

import com.telangana.digitallearning.dto.EvaluationSummaryDto;
import com.telangana.digitallearning.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluation")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @GetMapping("/records")
    public ResponseEntity<List<EvaluationSummaryDto>> getAllEvaluationRecords() {
        return ResponseEntity.ok(evaluationService.getAllEvaluationRecords());
    }

    @GetMapping("/summary-improvement")
    public ResponseEntity<Double> getOverallImprovement() {
        return ResponseEntity.ok(evaluationService.calculateOverallSystemImprovement());
    }
}
