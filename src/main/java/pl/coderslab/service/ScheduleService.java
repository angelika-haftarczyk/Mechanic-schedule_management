package pl.coderslab.service;

import pl.coderslab.exceptions.ScheduleException;
import pl.coderslab.model.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {

    Schedule addSchedule(Schedule schedule) throws ScheduleException;
    List<Schedule> findAllSchedule();
    Schedule findByStartTimeWork(LocalDateTime date);
    void deleteSchedule(Schedule schedule);
}
