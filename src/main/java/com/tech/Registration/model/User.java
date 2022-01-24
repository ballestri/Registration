package com.tech.Registration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import javax.validation.constraints.*;

/**
 * User Model class
 * @author Nelson
 */
@Data
@Entity
@Accessors(chain = true)
@NoArgsConstructor
public class User {

    private static final String PHONE_NUMBER_FORMAT = "^((\\+)33|0)[1-9](\\d{2}){4}$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthdate;

    @Column(nullable = false)
    private String country;

    @Pattern(regexp = PHONE_NUMBER_FORMAT)
    private String phoneNumber;

    private String gender;
}
