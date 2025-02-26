package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.repository.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public boolean canCreateReport(String UserId) {

        long maxReports = reportRepository.countByUserId(UserId);

        return maxReports < 2;
    }
}
