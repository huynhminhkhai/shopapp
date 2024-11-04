package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.dtos.CategoryDTO;
import com.huynhminhkhai.shopapp.dtos.WelcomeDTO;
import com.huynhminhkhai.shopapp.models.Welcome;

import java.util.List;

public interface IWelcomeService {
    //Thêm welcome
    Welcome createWelcome(WelcomeDTO welcomeDTO);
    // Lấy tất cả welcome
    List<Welcome> getAllWelcomes();
    // Lấy welcome theo ID
    Welcome getWelcomeById(Long welcomeId);
    // Cập nhật welcome
    Welcome updateWelcome(Long welcomeId, WelcomeDTO welcomeDTO);
    // Xóa welcome theo ID
    void deleteWelcome(Long welcomeId);
}
