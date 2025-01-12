package com.job.management.service;

import com.job.management.dto.EnquiryDTO;

import java.util.List;

public interface EnquiryService {
    EnquiryDTO createEnquiry(EnquiryDTO enquiryDTO);
    EnquiryDTO updateEnquiry(Long id, EnquiryDTO enquiryDTO);
    void deleteEnquiry(Long id);
    EnquiryDTO getEnquiryById(Long id);
    List<EnquiryDTO> getAllEnquiries();
}