package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;

import java.util.List;

public interface ScheduleAppRepository {

    ScheduleApp saveSchedule(ScheduleApp scheduleApp);
    List<ScheduleAppResponseDto> findAllSchedules();
    ScheduleApp findScheduleById(Long id);
    void deleteSchedule(Long id);
}
