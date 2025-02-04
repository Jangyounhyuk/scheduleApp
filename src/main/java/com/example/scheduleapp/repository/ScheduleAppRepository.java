package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;

import java.util.List;
import java.util.Optional;

public interface ScheduleAppRepository {

    ScheduleAppResponseDto saveSchedule(ScheduleApp scheduleApp);
    List<ScheduleAppResponseDto> findAllSchedules();
    Optional<ScheduleApp> findScheduleById(Long id);
    ScheduleApp findScheduleByIdOrElseThrow(Long id);
    int updateSchedule(Long id, String name, String password, String todo, String date);
    int deleteSchedule(Long id);
}
