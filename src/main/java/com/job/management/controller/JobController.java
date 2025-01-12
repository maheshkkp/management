package com.job.management.controller;

import com.job.management.domain.enums.Role;
import com.job.management.dto.JobDTO;
import com.job.management.security.annotation.RoleSecured;
import com.job.management.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @RoleSecured(roles = {Role.MANAGER})
    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO jobDTO) {
        return new ResponseEntity<>(jobService.createJob(jobDTO), HttpStatus.CREATED);
    }

    @RoleSecured(roles = {Role.MANAGER})
    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable Long id, @RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok(jobService.updateJob(id, jobDTO));
    }

    @RoleSecured(roles = {Role.MANAGER})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @RoleSecured(roles = {Role.MANAGER, Role.EMPLOYEE})
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @RoleSecured(roles = {Role.MANAGER, Role.EMPLOYEE})
    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }
}