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
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        if(model.containsAttribute("error")) {
            System.out.println("error: "+model.getAttribute("error"));
        } else {
            System.out.println("model pusty");
        }
        if(httpServletRequest.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("schedules", scheduleService.findAllSchedule()); //TODO zmień na DTO
            return "admin/schedule";
        } else {
            List<Schedule> allSchedule = scheduleService.findAllSchedule();
            String login = httpServletRequest.getRemoteUser();
            List<ScheduleDto> schedules = new ArrayList<>();
            for (Schedule schedule : allSchedule) {
                ScheduleDto scheduleDto = new ScheduleDto();
                scheduleDto.setStartTime(schedule.getStartTimeWork());
                LocalDateTime endTime = schedule.getStartTimeWork().plusMinutes(schedule.getService().getDurationInMinutes());
                scheduleDto.setEndTime(endTime);
                if (!schedule.getUser().getLogin().equals(login)) {
//                    Product product = new Product();
//                    product.setDurationInMinutes(schedule.getService().getDurationInMinutes());
//                    schedule.setService(product);
                    scheduleDto.setName("niedostępne");
                } else {
                    scheduleDto.setName(schedule.getService().getServiceName());
                }
                schedules.add(scheduleDto);
            }
                model.addAttribute("schedules", schedules);
                return "schedule";
        }
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
        String service = httpServletRequest.getParameter("service");
        String noteText = httpServletRequest.getParameter("note");
        Long id = Long.parseLong(service);
        User user = userService.findByLogin(httpServletRequest.getRemoteUser());
        Product product = productService.getProductById(id);
        Schedule schedule = new Schedule();
        schedule.setUser(user);
        schedule.setStartTimeWork(date);
        schedule.setService(product);

        Note note = new Note();
        note.setNote(noteText);
        note.setUser(user);
        try {
            Schedule scheduleFromDB = scheduleService.addSchedule(schedule);
            note.setSchedule(scheduleFromDB);
            if(noteText != null && !noteText.isEmpty()){
                noteService.saveNote(note);
            }

        } catch (ScheduleException e) {
            redirectAttributes.addFlashAttribute("error", "wybierz inny termin");
        }

        RedirectView redirectView = new RedirectView("/schedule",true);
        return redirectView;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                       Model model, HttpServletRequest httpServletRequest) {
        Schedule schedule = scheduleService.findByStartTimeWork(date);
        User user = userService.findByLogin(httpServletRequest.getRemoteUser());
        if(schedule != null && schedule.getId() != null && schedule.getUser().equals(user)) {
            model.addAttribute("startTime", date.toLocalTime());
            model.addAttribute("dateTime", date.toLocalDate());
            model.addAttribute("products", productService.findAllActive());
            model.addAttribute("notes", schedule.getNotes());
            model.addAttribute("serviceId", schedule.getService().getId());
            return "user/scheduleEdit";
        }
        return "redirect:/schedule";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String update(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                         HttpServletRequest httpServletRequest) {
        Schedule schedule = scheduleService.findByStartTimeWork(date);
        User user = userService.findByLogin(httpServletRequest.getRemoteUser());
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
