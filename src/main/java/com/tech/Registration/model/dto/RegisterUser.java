package com.tech.Registration.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.tech.Registration.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;

/**
 * RegisterUser model class
 * @author nelson
 *
 */
@Data
@Accessors(chain = true)
@Schema(description = "User Data Transfer Object class")
public class RegisterUser {

    private static final String PHONE_NUMBER_FORMAT = "^((\\+)33|0)[1-9](\\d{2}){4}$";

    /**
     * Register username
     */
    @Schema(description = "Register username", required = true, example = "nelson")
    @NotEmpty(message = "Username should not be empty")
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * Register birthdate
     */
    @NotNull(message = "The date of birth is required.")
    @Schema(description = "Register birthdate", format = "yyyy-MM-dd", required = true, example = "1996-01-01")
    @Past(message = "The date of birth must be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;

    /**
     * Register residence Country
     */
    @Schema(description = "Register residence Country", required = true, example = "France")
    @NotEmpty(message = "Country should not be empty")
    @NotBlank(message = "Country is mandatory")
    private String country;

    /**
     * Register phone number
     */
    @Size(max = 10)
    @Pattern(regexp = PHONE_NUMBER_FORMAT, message = "Please provide a valid phone number")
    @Schema(description = "Register phone number",example = "0661751923")
    private String phoneNumber;

    /**
     * Register gender
     */
    @Schema(description = "Register gender", example = "Male")
    private String gender;

    public User toUser() {
        return new User()
                .setUsername(username)
                .setBirthdate(birthDate)
                .setCountry(country)
                .setPhoneNumber(phoneNumber)
                .setGender(gender);
    }

}
