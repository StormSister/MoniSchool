package com.monika.kindergarden.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Person {
    private String name;
}
