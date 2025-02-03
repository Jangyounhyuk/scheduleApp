package com.example.scheduleapp.entity;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleApp {
    private Long id;
    private String name;
    private String password;
    private String todo;
    private String date;

    public ScheduleApp(ScheduleAppRequestDto scheduleAppRequestDto) {
        this.name = scheduleAppRequestDto.getName();
        this.password = scheduleAppRequestDto.getPassword();
        this.todo = scheduleAppRequestDto.getTodo();
        this.date = scheduleAppRequestDto.getDate();
    }

    public void update(ScheduleAppRequestDto requestDto) {
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.todo = requestDto.getTodo();
        this.date = requestDto.getDate();
    }
}
