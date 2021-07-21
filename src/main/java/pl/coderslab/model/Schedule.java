package pl.coderslab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Schedule extends BaseEntity {

    private LocalDateTime startTimeWork;
    @ManyToOne
    private Product service;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "schedule")
    private List<Note> notes;

    private boolean accepted;
}
