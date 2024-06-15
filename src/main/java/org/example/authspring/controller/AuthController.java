package org.example.authspring.controller;


import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.example.authspring.model.dto.request.AppUserRequest;
import org.example.authspring.model.dto.request.AuthRequest;
import org.example.authspring.model.dto.request.PasswordRequest;
import org.example.authspring.model.dto.response.AppUserResponse;
import org.example.authspring.model.dto.response.AuthResponse;
import org.example.authspring.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auths")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AppUserRequest appUserRequest) throws Exception{
        AppUserResponse res = authService.register(appUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        AuthResponse authResponse = authService.login(authRequest);
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @PutMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam @Positive(message = "Otp code cannot be negative or zero") String otpCode) {
        authService.verify(otpCode);
        return ResponseEntity.status(HttpStatus.OK).body("Your account is verify successful");
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resend(@RequestParam String email) throws MessagingException {
        authService.resend(email);
        return ResponseEntity.status(HttpStatus.OK).body("Resend otp code successful");
    }

    @PutMapping("/forget")
    public ResponseEntity<?> forget(@RequestParam String email, @Valid @RequestBody PasswordRequest passwordRequest) {
        authService.forget(email, passwordRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Your password is reset successful");
    }

}
