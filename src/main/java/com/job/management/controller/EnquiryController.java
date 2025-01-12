package com.job.management.controller;

import com.job.management.domain.enums.Role;
import com.job.management.dto.EnquiryDTO;
import com.job.management.security.annotation.RoleSecured;
import com.job.management.service.EnquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    private final EnquiryService enquiryService;

    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @RoleSecured(roles = {Role.MANAGER})
    @PostMapping
    public ResponseEntity<EnquiryDTO> createEnquiry(@RequestBody EnquiryDTO enquiryDTO) {
        return new ResponseEntity<>(enquiryService.createEnquiry(enquiryDTO), HttpStatus.CREATED);
    }

    @RoleSecured(roles = {Role.MANAGER})
    @PutMapping("/{id}")
    public ResponseEntity<EnquiryDTO> updateEnquiry(@PathVariable Long id, @RequestBody EnquiryDTO enquiryDTO) {
        return ResponseEntity.ok(enquiryService.updateEnquiry(id, enquiryDTO));
    }

    @RoleSecured(roles = {Role.MANAGER})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnquiry(@PathVariable Long id) {
        enquiryService.deleteEnquiry(id);
        return ResponseEntity.noContent().build();
    }

    @RoleSecured(roles = {Role.MANAGER, Role.EMPLOYEE})
    @GetMapping("/{id}")
    public ResponseEntity<EnquiryDTO> getEnquiryById(@PathVariable Long id) {
        return ResponseEntity.ok(enquiryService.getEnquiryById(id));
    }

    @RoleSecured(roles = {Role.MANAGER, Role.EMPLOYEE})
    @GetMapping
    public ResponseEntity<List<EnquiryDTO>> getAllEnquiries() {
        return ResponseEntity.ok(enquiryService.getAllEnquiries());
    }
}