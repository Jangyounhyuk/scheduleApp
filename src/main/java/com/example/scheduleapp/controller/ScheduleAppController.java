package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/schedules") //prefix
public class ScheduleAppController {
    private final Map<Long, ScheduleApp> scheduleList = new HashMap<>();

    @PostMapping
    public ScheduleAppResponseDto createSchedule(@RequestBody ScheduleAppRequestDto requestDto) {
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;
        ScheduleApp scheduleApp = new ScheduleApp(scheduleId, requestDto.getName(), requestDto.getPassword(), requestDto.getTodo(), requestDto.getDate());
        scheduleList.put(scheduleId, scheduleApp);
        return new ScheduleAppResponseDto(scheduleApp);
    }

    @GetMapping
    public List<ScheduleAppResponseDto> findAllSchedules() {
        List<ScheduleAppResponseDto> responseLIst = new ArrayList<>();
        for (ScheduleApp scheduleApp : scheduleList.values()) {
            ScheduleAppResponseDto responseDto = new ScheduleAppResponseDto(scheduleApp);
            responseLIst.add(responseDto);
        }
        return responseLIst;
    }

    @GetMapping("/{id}")
    public ScheduleAppResponseDto findScheduleById(@PathVariable Long id) {
        ScheduleApp scheduleApp = scheduleList.get(id);
        return new ScheduleAppResponseDto(scheduleApp);
    }

    @PutMapping("/{id}")
    public ScheduleAppResponseDto updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleAppRequestDto requestDto
    ) {
        ScheduleApp scheduleApp = scheduleList.get(id);
        scheduleApp.update(requestDto);
        return new ScheduleAppResponseDto(scheduleApp);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleList.remove(id);
    }
}
