package com.example.scheduleapp.entity;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ScheduleApp {

    @Setter
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

    public void update(ScheduleAppRequestDto requestDto) {
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.todo = requestDto.getTodo();
        this.date = requestDto.getDate();
    }
}
