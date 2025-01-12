package com.job.management.mapper;

import com.job.management.domain.Job;
import com.job.management.dto.JobDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper extends BasicMapper<Job, JobDTO> {
}
