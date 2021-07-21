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
        LocalDateTime startDate = startTimeWork.withHour(8).withMinute(0);
        LocalDateTime endDate = startTimeWork.withHour(18).withMinute(0);
        List<Schedule> scheduleByDates = scheduleRepository.findScheduleByDates(startDate, endDate); // wybieram wszystkie harmonogramy danego dnia
        for (Schedule scheduleFromDB : scheduleByDates) {
            LocalDateTime startDB = scheduleFromDB.getStartTimeWork();
            LocalDateTime endDB = startDB.plusMinutes(scheduleFromDB.getService().getDurationInMinutes());
            if(startTimeWork.isBefore(startDB) && startTimeWork.isAfter(endDB)) {
                throw new ScheduleException();
            }
            if(endTimeWork.isAfter(startDB) && endTimeWork.isBefore(endDB)) {
                throw new ScheduleException();
            }
            if(startTimeWork.isEqual(startDB) && endTimeWork.isEqual(endDB)) {
                throw new ScheduleException();
            }
        }
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> findAllSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findByStartTimeWork(LocalDateTime date) {
        return scheduleRepository.findByStartTimeWork(date);
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        if(schedule != null && schedule.getId() != null) {
            scheduleRepository.delete(schedule);
        }
    }

    @Override
    public void update(Schedule schedule) {
        scheduleRepository.save(schedule);
    }
}
