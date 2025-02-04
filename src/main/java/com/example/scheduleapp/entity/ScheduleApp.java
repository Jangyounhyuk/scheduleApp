package com.example.scheduleapp.entity;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleApp {

    private Long id;
    private String name;
    private String password;
    private String todo;
    private String date;

    public ScheduleApp(String name, String password, String todo, String date) {
        this.name = name;
        this.password = password;
        this.todo = todo;
        this.date = date;
    }

    public ScheduleApp(long id, String name, String todo, String date) {
        this.id = id;
        this.name = name;
        this.todo = todo;
        this.date = date;
    }

    public void update(ScheduleAppRequestDto requestDto) {
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.todo = requestDto.getTodo();
        this.date = requestDto.getDate();
    }
}
