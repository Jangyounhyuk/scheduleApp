package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleAppRequestDto;
import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;
import com.example.scheduleapp.repository.ScheduleAppRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        return scheduleAppRepository.saveSchedule(scheduleApp);
    }

    @Override
    public List<ScheduleAppResponseDto> findAllSchedules() {

        return scheduleAppRepository.findAllSchedules();
    }

    @Override
    public ScheduleAppResponseDto findScheduleById(Long id) {

        ScheduleApp scheduleApp = scheduleAppRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleAppResponseDto(scheduleApp);
    }

    @Transactional
    @Override
    public ScheduleAppResponseDto updateSchedule(Long id, ScheduleAppRequestDto requestDto) {

        if(requestDto.getName() == null || requestDto.getPassword() == null || requestDto.getTodo() == null || requestDto.getDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The contents are required values.");
        }

        int updatedRow = scheduleAppRepository.updateSchedule(id, requestDto.getName(), requestDto.getPassword(), requestDto.getTodo(), requestDto.getDate());

        if(updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        ScheduleApp scheduleApp = scheduleAppRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleAppResponseDto(scheduleApp);
    }

    @Override
    public void deleteSchedule(Long id) {

        int deletedRow = scheduleAppRepository.deleteSchedule(id);

        if(deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }
}
