package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.exceptions.ScheduleException;
import pl.coderslab.model.Schedule;
import pl.coderslab.repository.ScheduleRepository;
import pl.coderslab.service.ScheduleService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public Schedule addSchedule(Schedule schedule) throws ScheduleException {
        LocalDateTime startTimeWork = schedule.getStartTimeWork();
        LocalDateTime endTimeWork = startTimeWork.plusMinutes(schedule.getService().getDurationInMinutes());
        // TODO sprawdzić czy mamy czas dla użytkownika!!!

        if(schedule.getStartTimeWork().getMinute() != 0) {
            throw new ScheduleException();
        }
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> findAllSchedule() {
        return scheduleRepository.findAll();
    }
}
