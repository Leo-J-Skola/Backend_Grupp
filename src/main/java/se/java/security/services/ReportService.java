package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportService reportService;

    private final ReportRepository reportRepository;

    public ReportService(ReportService reportService, ReportRepository reportRepository) {
        this.reportService = reportService;
        this.reportRepository = reportRepository;
    }
}
