package com.iwa.iwaid.consultoriomedico.services;

import com.iwa.iwaid.consultoriomedico.dto.UserDTO;
import com.iwa.iwaid.consultoriomedico.entity.User;
import com.iwa.iwaid.consultoriomedico.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDTO getUserByEmail(String email){
        User user= userRepository.findByEmail(email);
        return UserDTO.build(user);
    }
}
