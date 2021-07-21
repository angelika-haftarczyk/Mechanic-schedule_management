package pl.coderslab.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDto {
    LocalDateTime startTime;
    LocalDateTime endTime;
    String name;
    String userName;


}
