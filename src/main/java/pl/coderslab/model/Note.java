package pl.coderslab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Note extends BaseEntity{

    private String note;

    @ManyToOne
    private User user;

    @ManyToOne
    private Schedule schedule;

}
