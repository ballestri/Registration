package com.tech.Registration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;

@Entity
@Data
public class User {

    private static final String PHONE_NUMBER_FORMAT="^((\\+)33|0)[1-9](\\d{2}){4}$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull(message = "Username should not be empty")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotNull(message = "Birthdate should not be empty")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdate;

    @NotNull(message = "Country should not be empty")
    @NotBlank(message = "Country is mandatory")
    private String country;

    @Size(max = 10)
    @Pattern(regexp = PHONE_NUMBER_FORMAT, message = "Please provide a valid phone number")
    private String phoneNumber;
    private String gender;

    public User(){}

    public User(String username, Date birthdate, String country) {
        this.username = username;
        this.birthdate = birthdate;
        this.country = country;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + this.username + ", birthdate=" + birthdate + ", country=" + this.country
                + ", phoneNumber=" + this.phoneNumber + ", gender=" + this.gender +"]";
    }
}
