package com.example.scheduleapp.dto;

import com.example.scheduleapp.entity.ScheduleApp;
import lombok.Getter;

@Getter
public class ScheduleAppResponseDto {
    private Long id;
    private String name;
    private String todo;
    private String date;

    public ScheduleAppResponseDto(ScheduleApp scheduleApp) {
        this.id = scheduleApp.getId();
        this.name = scheduleApp.getName();
        this.todo = scheduleApp.getTodo();
        this.date = scheduleApp.getDate();
    }
}
