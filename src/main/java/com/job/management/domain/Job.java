package com.job.management.domain;

import com.job.management.domain.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime start;

    private LocalDateTime end;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false, name = "description")
    private String content;

    @Column
    private String colorPrimary;

    @Column
    private String colorSecondary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private boolean isClickable;

    private boolean isDisabled;

    private boolean draggable;

    private boolean resizableBeforeStart;

    private boolean resizableAfterEnd;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
}