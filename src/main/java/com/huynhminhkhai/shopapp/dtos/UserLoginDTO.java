package com.huynhminhkhai.shopapp.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
