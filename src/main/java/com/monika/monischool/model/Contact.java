package com.monika.monischool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;
    @NotBlank(message = "name must not be blank")
    @Size(min = 3, message = "name must be at least 3 charakters long")
    private String name;

    @NotBlank(message = "mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be exactly 10 digits long")
    private String mobileNum;

    @NotBlank(message = "email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "subject must not be blank")
    @Size(min = 5, message = "subject must be at least 5 characters long")
    private String subject;

    @NotBlank(message = "message must not be blank")
    @Size(min = 10, message = "message must be at least 10 characters long")
    private String message;

    private String status;


}

