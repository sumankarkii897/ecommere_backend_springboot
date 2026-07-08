package com.sumankarki.Ecommerce.dto;

import com.sumankarki.Ecommerce.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Password is required")

    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")

    private String email;

    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;

    private UserRole role;



}
