package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;

import java.util.List;

public interface ScheduleAppService {

    ScheduleAppResponseDto saveSchedule(ScheduleAppRequestDto requestDto);

    List<ScheduleAppResponseDto> findAllSchedules();
    ScheduleAppResponseDto findScheduleById(Long id);
    ScheduleAppResponseDto updateSchedule(Long id, ScheduleAppRequestDto requestDto);
    void deleteSchedule(Long id);
}
