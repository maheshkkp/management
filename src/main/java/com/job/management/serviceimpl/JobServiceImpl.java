package com.job.management.serviceimpl;

import com.job.management.domain.Job;
import com.job.management.dto.JobDTO;
import com.job.management.mapper.JobMapper;
import com.job.management.repository.JobRepository;
import com.job.management.service.JobService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    public JobDTO createJob(JobDTO jobDTO) {
        Job job = jobMapper.toEntity(jobDTO);
        job = jobRepository.save(job);
        return jobMapper.toDTO(job);
    }

    @Override
    public JobDTO updateJob(Long id, JobDTO jobDTO) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
        existingJob.setTitle(jobDTO.getTitle());
        existingJob.setContent(jobDTO.getContent());
        existingJob.setStart(jobDTO.getStart());
        existingJob.setEnd(jobDTO.getEnd());
        existingJob.setColorPrimary(jobDTO.getColor().getPrimary());
        existingJob.setColorSecondary(jobDTO.getColor().getSecondary());
        existingJob.setStatus(jobDTO.getStatus());
        existingJob.setClickable(jobDTO.isClickable());
        existingJob.setDisabled(jobDTO.isDisabled());
        existingJob.setDraggable(jobDTO.isDraggable());
        existingJob.setResizableBeforeStart(jobDTO.getResizable().isBeforeStart());
        existingJob.setResizableAfterEnd(jobDTO.getResizable().isAfterEnd());
        existingJob.setUpdatedAt(LocalDateTime.now());
        Job updatedJob = jobRepository.save(existingJob);
        return jobMapper.toDTO(updatedJob);
    }

    @Override
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Job not found with id: " + id);
        }
        jobRepository.deleteById(id);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
        return jobMapper.toDTO(job);
    }

    @Override
    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(jobMapper::toDTO)
                .collect(Collectors.toList());
    }
}