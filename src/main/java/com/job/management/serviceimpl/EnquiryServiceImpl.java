package com.job.management.serviceimpl;

import com.job.management.domain.Enquiry;
import com.job.management.dto.EnquiryDTO;
import com.job.management.mapper.EnquiryMapper;
import com.job.management.repository.EnquiryRepository;
import com.job.management.service.EnquiryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final EnquiryMapper enquiryMapper;

    public EnquiryServiceImpl(EnquiryRepository enquiryRepository, EnquiryMapper enquiryMapper) {
        this.enquiryRepository = enquiryRepository;
        this.enquiryMapper = enquiryMapper;
    }

    @Override
    public EnquiryDTO createEnquiry(EnquiryDTO enquiryDTO) {
        Enquiry enquiry = enquiryMapper.toEntity(enquiryDTO);
        enquiry = enquiryRepository.save(enquiry);
        return enquiryMapper.toDTO(enquiry);
    }

    @Override
    public EnquiryDTO updateEnquiry(Long id, EnquiryDTO enquiryDTO) {
        Enquiry existingEnquiry = enquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + id));
        existingEnquiry.setTitle(enquiryDTO.getTitle());
        existingEnquiry.setContent(enquiryDTO.getContent());
        existingEnquiry.setUpdatedAt(enquiryDTO.getUpdatedAt());
        Enquiry updatedEnquiry = enquiryRepository.save(existingEnquiry);
        return enquiryMapper.toDTO(updatedEnquiry);
    }

    @Override
    public void deleteEnquiry(Long id) {
        if (!enquiryRepository.existsById(id)) {
            throw new RuntimeException("Enquiry not found with id: " + id);
        }
        enquiryRepository.deleteById(id);
    }

    @Override
    public EnquiryDTO getEnquiryById(Long id) {
        Enquiry enquiry = enquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + id));
        return enquiryMapper.toDTO(enquiry);
    }

    @Override
    public List<EnquiryDTO> getAllEnquiries() {
        return enquiryRepository.findAll().stream()
                .map(enquiryMapper::toDTO)
                .collect(Collectors.toList());
    }
}