package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.dtos.UserDTO;
import com.huynhminhkhai.shopapp.models.User;
import com.huynhminhkhai.shopapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    @Override
    public User createUser(UserDTO userDTO) {
        String email = userDTO.getEmail();
        if(userRepository.existsByEmail(email)){
            throw new DataIntegrityViolationException("Email đã tồn tại");
        }
        User newUser = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public String login(String email, String password) {
        return null;
    }
}
