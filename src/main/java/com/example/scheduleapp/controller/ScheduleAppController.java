package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.service.ScheduleAppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules") //prefix
public class ScheduleAppController {

    private final ScheduleAppService scheduleAppService;

    public ScheduleAppController(ScheduleAppService scheduleAppService) {
        this.scheduleAppService = scheduleAppService;
    }

    @PostMapping
    public ResponseEntity<ScheduleAppResponseDto> createSchedule(@RequestBody ScheduleAppRequestDto requestDto) {


        return new ResponseEntity<>(scheduleAppService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleAppResponseDto> findAllSchedules() {

        return scheduleAppService.findAllSchedules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleAppResponseDto> findScheduleById(@PathVariable Long id) {

        return new ResponseEntity<>(scheduleAppService.findScheduleById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleAppResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleAppRequestDto requestDto
    ) {
        return new ResponseEntity<>(scheduleAppService.updateSchedule(id, requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {

//        if(scheduleList.containsKey(id)) {
//            scheduleList.remove(id);
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
        scheduleAppService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
