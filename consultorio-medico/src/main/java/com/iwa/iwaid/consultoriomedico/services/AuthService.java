package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.repository.UserRepository;
import com.iwa.iwaid.consultoriomedico.requests.LoginRequest;
import com.iwa.iwaid.consultoriomedico.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Value("${not.found}")
    private String notFound;

    public AuthResponse login(LoginRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        validateUserIfExists(request.getEmail());
        UserDetails user = userRepository.findByEmail(request.getEmail());
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
    private void validateUserIfExists(final String email) throws Exception{
        if(userRepository.findByEmail(email)==null){
            throw new Exception(notFound);
        }
    }
}
