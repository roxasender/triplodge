package com.nexus.triplodge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.triplodge.dto.ReportDto;
import com.nexus.triplodge.service.IReportService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/admin/reports")
@RequiredArgsConstructor
public class ReportController {
    private final IReportService reportService;

    // GET /api/admin/reports: Generates analytics and reports for the admin dashboard
    @GetMapping("/")
    public ResponseEntity<ReportDto> getAdminReports() {
        ReportDto report = reportService.generateReports();
        return ResponseEntity.ok(report);
    }
    
}
