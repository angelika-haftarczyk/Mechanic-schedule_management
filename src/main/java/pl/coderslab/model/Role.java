package pl.coderslab.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Role extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy="roles")
    Set<User> users;

}
