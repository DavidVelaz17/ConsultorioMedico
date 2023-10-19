package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.requests.LoginRequest;
import com.iwa.iwaid.consultoriomedico.responses.AuthResponse;
import com.iwa.iwaid.consultoriomedico.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iwaid/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) throws Exception {
        return ResponseEntity.ok(authService.login(request));
    }
}
