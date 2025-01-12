package com.job.management.service;

import com.job.management.dto.JobDTO;

import java.util.List;

public interface JobService {
    JobDTO createJob(JobDTO jobDTO);
    JobDTO updateJob(Long id, JobDTO jobDTO);
    void deleteJob(Long id);
    JobDTO getJobById(Long id);
    List<JobDTO> getAllJobs();
}
