package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.dtos.UserDTO;
import com.huynhminhkhai.shopapp.models.User;
import com.huynhminhkhai.shopapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        String email = userDTO.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new DataIntegrityViolationException("Email đã tồn tại");
        }
        User newUser = User.builder()
                .name(userDTO.getName())
                .email(email)
                .password(userDTO.getPassword())  // Mã hóa mật khẩu
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public String login(String email, String password) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));

        if (optionalUser.isPresent() && password.equals(optionalUser.get().getPassword())) {
            return "Đăng nhập thành công!";
        } else {
            return "Email hoặc mật khẩu không đúng";
        }
    }

}
