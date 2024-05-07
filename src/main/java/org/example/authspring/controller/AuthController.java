package org.example.authspring.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.authspring.model.dto.request.AppUserRequest;
import org.example.authspring.model.dto.response.AppUserResponse;
import org.example.authspring.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
