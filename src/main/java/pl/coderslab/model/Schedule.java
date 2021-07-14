package pl.coderslab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Schedule extends BaseEntity {

    private LocalDateTime startTimeWork;
    @ManyToOne
    private Product service;
    @ManyToOne
    private User user;

}
