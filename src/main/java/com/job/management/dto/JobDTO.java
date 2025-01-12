package com.job.management.dto;

import com.job.management.domain.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobDTO {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;
    private String content;
    private Color color;
    private String actions;
    private Status status;
    private boolean isClickable;
    private boolean isDisabled;
    private boolean draggable;
    private Resizable resizable;

    @Data
    public static class Color {
        private String primary;
        private String secondary;
    }

    @Data
    public static class Resizable {
        private boolean beforeStart;
        private boolean afterEnd;
    }
}