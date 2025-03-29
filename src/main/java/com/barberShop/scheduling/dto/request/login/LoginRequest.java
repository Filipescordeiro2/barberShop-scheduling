package com.barberShop.scheduling.dto.request.login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

        @NotNull(message = "Login cannot be null")
        private String login;
        @NotNull(message = "Password cannot be null")
        private String password;
}
