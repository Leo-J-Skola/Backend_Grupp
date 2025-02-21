package se.java.security.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.models.Report;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportController reportController;
    private final ReportService reportService;
    private final ReportRepository reportRepository;

    public ReportController(ReportController reportController, ReportService reportService, ReportRepository reportRepository) {
        this.reportController = reportController;
        this.reportService = reportService;
        this.reportRepository = reportRepository;
    }

    // list all report objects
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        // list all reports
        List<Report> report = reportRepository.findAll();

        // return values of all report objects
        return ResponseEntity.ok(report);
    }

    // get specific report
    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificReport(@PathVariable String id) {
        // check if report id exists, or throw
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found"));

        // return values of the report object
        return ResponseEntity.ok(report);
    }

    // update report details
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReport(@PathVariable String id, @Valid @RequestBody Report reportDetails) {
        // check if report id exists, or throw
        Report existingReport = reportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found"));

        // change report details
        existingReport.setUser_id(reportDetails.getUser_id());
        existingReport.setListing_id(reportDetails.getListing_id());
        existingReport.setHost(reportDetails.getHost());
        existingReport.setDescription(reportDetails.getDescription());
        existingReport.setPhoto(reportDetails.getPhoto());

        // return values of the report object
        return ResponseEntity.ok(reportRepository.save(existingReport));
    }

    // delete a report object
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable String id) {
        // check if report id exists, or throw
        if(!reportRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        }

        // delete the object
        reportRepository.deleteById(id);

        // return no values
        return ResponseEntity.noContent().build();
    }
}