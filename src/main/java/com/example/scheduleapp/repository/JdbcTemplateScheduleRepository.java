package com.example.scheduleapp.repository;

import com.example.scheduleapp.dto.ScheduleAppResponseDto;
import com.example.scheduleapp.entity.ScheduleApp;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleAppRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleAppResponseDto saveSchedule(ScheduleApp scheduleApp) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", scheduleApp.getName());
        parameters.put("password", scheduleApp.getPassword());
        parameters.put("todo", scheduleApp.getTodo());
        parameters.put("date", scheduleApp.getDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleAppResponseDto(key.longValue(), scheduleApp.getName(), scheduleApp.getTodo(), scheduleApp.getDate());
    }

    @Override
    public List<ScheduleAppResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    @Override
    public Optional<ScheduleApp> findScheduleById(Long id) {
        List<ScheduleApp> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public ScheduleApp findScheduleByIdOrElseThrow(Long id) {
        List<ScheduleApp> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id =" + id));
    }

    @Override
    public int updateSchedule(Long id, String name, String password, String todo, String date) {
        return jdbcTemplate.update("update schedule set name = ?, password = ?, todo = ?, date = ? where id = ?", name, password, todo, date);
    }


    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where id = ?", id);
    }

    private RowMapper<ScheduleAppResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleAppResponseDto>() {
            @Override
            public ScheduleAppResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleAppResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("todo"),
                        rs.getString("date")
                );
            }
        };
    }

    private RowMapper<ScheduleApp> scheduleRowMapperV2() {
        return new RowMapper<ScheduleApp>() {
            @Override
            public ScheduleApp mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleApp(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("todo"),
                        rs.getString("date")
                );
            }
        };
    }
}
