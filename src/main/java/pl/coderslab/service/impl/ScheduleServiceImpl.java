package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.Schedule;
import pl.coderslab.repository.ScheduleRepository;
import pl.coderslab.service.ScheduleService;

import java.time.LocalDateTime;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public Schedule addSchedule(Schedule schedule) {
        LocalDateTime startTimeWork = schedule.getStartTimeWork();
        LocalDateTime endTimeWork = startTimeWork.plusMinutes(schedule.getService().getDurationInMinutes());


        return scheduleRepository.save(schedule);
    }
}
