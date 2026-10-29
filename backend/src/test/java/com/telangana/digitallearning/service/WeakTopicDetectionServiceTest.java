package com.telangana.digitallearning.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class WeakTopicDetectionServiceTest {

    private WeakTopicDetectionService detectionService;

    @BeforeEach
    void setUp() {
        detectionService = new WeakTopicDetectionService(null, null, null, null);
    }

    @Test
    @DisplayName("Score below 40% should classify as WEAK")
    void testWeakThreshold() {
        String status = detectionService.evaluatePerformanceStatus(35.0);
        assertEquals("WEAK", status);
    }

    @Test
    @DisplayName("Score between 40% and 60% should classify as NEEDS_IMPROVEMENT")
    void testNeedsImprovementThreshold() {
        String status = detectionService.evaluatePerformanceStatus(50.0);
        assertEquals("NEEDS_IMPROVEMENT", status);
    }

    @Test
    @DisplayName("Score above 60% should classify as GOOD")
    void testGoodThreshold() {
        String status = detectionService.evaluatePerformanceStatus(75.0);
        assertEquals("GOOD", status);
    }
}
