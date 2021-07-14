package pl.coderslab.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Product extends BaseEntity {

    private String serviceName;
    private String description;
    private double price;
    private int durationInMinutes;
    private boolean available;

}
