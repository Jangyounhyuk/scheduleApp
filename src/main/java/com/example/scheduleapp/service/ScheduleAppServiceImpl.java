package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;
import com.example.scheduleapp.repository.ScheduleAppRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleAppServiceImpl implements ScheduleAppService {

    private final ScheduleAppRepository scheduleAppRepository;

    public ScheduleAppServiceImpl(ScheduleAppRepository scheduleAppRepository) {
        this.scheduleAppRepository = scheduleAppRepository;
    }


    @Override
    public ScheduleAppResponseDto saveSchedule(ScheduleAppRequestDto requestDto) {

        ScheduleApp scheduleApp = new ScheduleApp(requestDto.getName(), requestDto.getPassword(), requestDto.getTodo(), requestDto.getDate());

        ScheduleApp savedSchedule = scheduleAppRepository.saveSchedule(scheduleApp);

        return new ScheduleAppResponseDto(savedSchedule);
    }

    @Override
    public List<ScheduleAppResponseDto> findAllSchedules() {

        return scheduleAppRepository.findAllSchedules();
    }

    @Override
    public ScheduleAppResponseDto findScheduleById(Long id) {

        ScheduleApp schedule = scheduleAppRepository.findScheduleById(id);

        if(schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new ScheduleAppResponseDto(schedule);
    }

    @Override
    public ScheduleAppResponseDto updateSchedule(Long id, ScheduleAppRequestDto requestDto) {

        ScheduleApp schedule = scheduleAppRepository.findScheduleById(id);

        if(schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        if(requestDto.getName() == null || requestDto.getPassword() == null || requestDto.getTodo() == null || requestDto.getDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The contents are required values.");
        }

        schedule.update(requestDto);

        return new ScheduleAppResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        ScheduleApp schedule = scheduleAppRepository.findScheduleById(id);

        if(schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        scheduleAppRepository.deleteSchedule(id);
    }
}
