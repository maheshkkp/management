package com.job.management.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnquiryDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
