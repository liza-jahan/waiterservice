package com.example.waiterservice.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {

    //@NotBlank(message = "Full name is required")
   // @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String name;

    //@NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
   // @Pattern(regexp = "^(\\+8801[3-9]\\d{8})$", message = "Provide a valid Bangladeshi phone number (e.g., +8801XXXXXXXXX)")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Occupation is required")
    private String occupation;

    @NotNull(message = "Date of birth is required")
    private Date dateOfBirth;

    @NotBlank(message = "National ID card number is required")
  //  @Size(min = 10, max = 20, message = "National ID card number must be between 10 and 20 characters")
    private String nationalIdCard;

    @NotBlank(message = "Bkash number is required")
   // @Pattern(regexp = "^(\\+8801[3-9]\\d{8})$", message = "Provide a valid Bkash number (e.g., +8801XXXXXXXXX)")
    private String bkash;

    @NotBlank(message = "State is required")
    private String state;

    private String roles;
}
