package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.dtos.UserDTO;
import com.huynhminhkhai.shopapp.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO);
    String login(String email, String password);
}
