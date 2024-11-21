package com.huynhminhkhai.shopapp.controllers;

import com.huynhminhkhai.shopapp.dtos.UserDTO;
import com.huynhminhkhai.shopapp.dtos.UserLoginDTO;
import com.huynhminhkhai.shopapp.models.User;
import com.huynhminhkhai.shopapp.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ){
        try {
            if (result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return  ResponseEntity.badRequest().body(errorMessages);
            }
            if (!userDTO.getPassword().equals(userDTO.getRetypePassword())){
                return ResponseEntity.badRequest().body("Password does not match");
            }
            User registerUser = userService.createUser(userDTO);
            return ResponseEntity.ok(registerUser);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @Valid @RequestBody UserLoginDTO userLoginDTO
    ) {
        String response = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        if ("Đăng nhập thành công!".equals(response)) {
            return ResponseEntity.ok(userLoginDTO); // Thay "some token" bằng token thực tế nếu có
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
