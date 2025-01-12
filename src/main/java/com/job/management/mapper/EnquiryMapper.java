package com.job.management.mapper;

import com.job.management.domain.Enquiry;
import com.job.management.dto.EnquiryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnquiryMapper extends BasicMapper<Enquiry, EnquiryDTO> {
}
