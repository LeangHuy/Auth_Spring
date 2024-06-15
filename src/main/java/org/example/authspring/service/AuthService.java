package org.example.authspring.service;



import jakarta.mail.MessagingException;
import org.example.authspring.model.dto.request.AppUserRequest;
import org.example.authspring.model.dto.request.AuthRequest;
import org.example.authspring.model.dto.request.PasswordRequest;
import org.example.authspring.model.dto.response.AppUserResponse;
import org.example.authspring.model.dto.response.AuthResponse;

import java.util.UUID;

public interface AuthService {

    AuthResponse login(AuthRequest authRequest) throws Exception;

    AppUserResponse register(AppUserRequest appUserRequest) throws Exception;

    void verify(String otpCode);

    void resend(String email) throws MessagingException;

    void forget(String email, PasswordRequest passwordRequest) throws MessagingException;

}
