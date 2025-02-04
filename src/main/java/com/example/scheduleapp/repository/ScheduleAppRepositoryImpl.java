package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ScheduleAppRepositoryImpl implements ScheduleAppRepository {

    private final Map<Long, ScheduleApp> scheduleList = new HashMap<>();

    @Override
    public ScheduleApp saveSchedule(ScheduleApp scheduleApp) {

        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;
        scheduleApp.setId(scheduleId);

        scheduleList.put(scheduleId, scheduleApp);

        return scheduleApp;
    }

    @Override
    public List<ScheduleAppResponseDto> findAllSchedules() {

        List<ScheduleAppResponseDto> allSchedules = new ArrayList<>();

        for (ScheduleApp scheduleApp : scheduleList.values()) {
            ScheduleAppResponseDto responseDto = new ScheduleAppResponseDto(scheduleApp);
            allSchedules.add(responseDto);
        }

        return allSchedules;
    }

    @Override
    public ScheduleApp findScheduleById(Long id) {

        return scheduleList.get(id);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleList.remove(id);
    }

}
