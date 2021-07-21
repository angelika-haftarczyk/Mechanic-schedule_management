package pl.coderslab.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.exceptions.ScheduleException;
import pl.coderslab.model.Note;
import pl.coderslab.model.Product;
import pl.coderslab.model.Schedule;
import pl.coderslab.model.User;
import pl.coderslab.model.dto.ScheduleDto;
import pl.coderslab.service.NoteService;
import pl.coderslab.service.ProductService;
import pl.coderslab.service.ScheduleService;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    NoteService noteService;

    @RequestMapping("")
    public String index(HttpServletRequest httpServletRequest, Model model){
        List<Schedule> allSchedule = scheduleService.findAllSchedule();
        List<ScheduleDto> schedules = new ArrayList<>();
        String login = httpServletRequest.getRemoteUser();
        for (Schedule schedule : allSchedule) {
            ScheduleDto scheduleDto = new ScheduleDto();
            scheduleDto.setStartTime(schedule.getStartTimeWork());
            LocalDateTime endTime = schedule.getStartTimeWork().plusMinutes(schedule.getService().getDurationInMinutes());
            scheduleDto.setEndTime(endTime);
            if(!httpServletRequest.isUserInRole("ROLE_ADMIN") && !schedule.getUser().getLogin().equals(login)) {
                scheduleDto.setName("niedostępne");
                scheduleDto.setUserName("niedostępne");
                scheduleDto.setAccepted(false);
            } else {
                scheduleDto.setName(schedule.getService().getServiceName());
                scheduleDto.setUserName(schedule.getUser().getFirstName()+" "+schedule.getUser().getLastName());
                scheduleDto.setAccepted(schedule.isAccepted());
            }
            schedules.add(scheduleDto);
        }
        model.addAttribute("schedules", schedules);
        return "schedule";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date, Model model) {
        model.addAttribute("startTime", date.toLocalTime());
        model.addAttribute("dateTime", date.toLocalDate());
        model.addAttribute("products", productService.findAllActive());
        return "user/scheduleAdd";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public RedirectView add(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                            HttpServletRequest httpServletRequest,
                            RedirectAttributes redirectAttributes) {
        String service = httpServletRequest.getParameter("service"); // pobieram product
        String noteText = httpServletRequest.getParameter("note");
        Long serviceId = Long.parseLong(service);
        User user = userService.findByLogin(httpServletRequest.getRemoteUser());
        Product product = productService.getProductById(serviceId);
        Schedule schedule = new Schedule();
        schedule.setUser(user);
        schedule.setStartTimeWork(date);
        schedule.setService(product);
        schedule.setAccepted(false);
        Note note = new Note();
        note.setNote(noteText);
        note.setUser(user);
        try {
            Schedule scheduleFromDB = scheduleService.addSchedule(schedule);
            note.setSchedule(scheduleFromDB);
            if(noteText != null && !noteText.isEmpty()){
                noteService.saveNote(note);
            }

        } catch (ScheduleException e) { //FlashAttribute jest niewidoczny w url
            redirectAttributes.addFlashAttribute("error", "wybierz inny termin");
        }

        RedirectView redirectView = new RedirectView("/schedule",false);
        return redirectView;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                       Model model, HttpServletRequest httpServletRequest) {
        Schedule schedule = scheduleService.findByStartTimeWork(date);
        User user = userService.findByLogin(httpServletRequest.getRemoteUser());
        if(schedule != null && schedule.getId() != null &&
                (schedule.getUser().equals(user) || httpServletRequest.isUserInRole("ROLE_ADMIN"))) {
            model.addAttribute("startTime", date.toLocalTime());
            model.addAttribute("dateTime", date.toLocalDate());
            model.addAttribute("products", productService.findAllActive());
            model.addAttribute("notes", schedule.getNotes());
            model.addAttribute("serviceId", schedule.getService().getId());
            model.addAttribute("user", schedule.getUser());
            model.addAttribute("accepted", schedule.isAccepted());
            return "user/scheduleEdit";
        }
        return "redirect:/schedule";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String update(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                         HttpServletRequest httpServletRequest) {
        Schedule schedule = scheduleService.findByStartTimeWork(date);
        User user = userService.findByLogin(httpServletRequest.getRemoteUser());
        if(httpServletRequest.isUserInRole("ADMIN")) {
            boolean accepted = Optional.ofNullable(httpServletRequest.getParameter("accepted")).map(a -> a.equals("on")).orElse(false);
            schedule.setAccepted(accepted);
            scheduleService.update(schedule);
        }
        String noteText = httpServletRequest.getParameter("note");
        if(noteText != null && !noteText.isEmpty()) {
            Note note = new Note();
            note.setNote(noteText);
            note.setUser(user);
            note.setSchedule(schedule);
            noteService.saveNote(note);
        }
        return "redirect:/schedule";
    }


    @RequestMapping(value = "/confirm",method = RequestMethod.GET)
    public String confirm(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                          Model model, HttpServletRequest httpServletRequest) {
        Schedule schedule = scheduleService.findByStartTimeWork(date);
        User user = userService.findByLogin(httpServletRequest.getRemoteUser());
        if(schedule != null && schedule.getId() != null && schedule.getUser().equals(user)) {
            model.addAttribute("startTime", date.toLocalTime());
            model.addAttribute("dateTime", date.toLocalDate());
            model.addAttribute("products", productService.findAllActive());
            model.addAttribute("notes", schedule.getNotes());
            model.addAttribute("serviceId", schedule.getService().getId());
            return "user/scheduleConfirm";
        }
        return "redirect:/schedule";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        Schedule schedule = scheduleService.findByStartTimeWork(date);
        scheduleService.deleteSchedule(schedule);
        return "redirect:/schedule";
    }
}
