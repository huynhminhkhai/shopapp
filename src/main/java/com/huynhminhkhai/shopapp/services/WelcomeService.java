package com.huynhminhkhai.shopapp.services;

import com.huynhminhkhai.shopapp.dtos.WelcomeDTO;
import com.huynhminhkhai.shopapp.models.Category;
import com.huynhminhkhai.shopapp.models.Welcome;
import com.huynhminhkhai.shopapp.repositories.WelcomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WelcomeService implements IWelcomeService{

    private final WelcomeRepository welcomeRepository;

    @Override
    public Welcome createWelcome(WelcomeDTO welcomeDTO) {
        // Lưu welcome mới vào database
        Welcome newWelcome = Welcome.builder()
                .title(welcomeDTO.getTitle())
                .description(welcomeDTO.getDescription())
                .imageUrl(welcomeDTO.getImageUrl()).build();
        return welcomeRepository.save(newWelcome);
    }

    @Override
    public List<Welcome> getAllWelcomes() {
        return welcomeRepository.findAll();
    }

    @Override
    public Welcome getWelcomeById(Long welcomeId) {
        return welcomeRepository.findById(welcomeId).orElseThrow(() ->
                new RuntimeException("Welcome not found"));
    }

    @Override
    public Welcome updateWelcome(Long welcomeId, WelcomeDTO welcomeDTO) {
        Welcome existingWelcome = getWelcomeById(welcomeId);
        existingWelcome.setTitle(welcomeDTO.getTitle());
        existingWelcome.setDescription(welcomeDTO.getDescription());
        // Nếu imageUrl mới không phải là null, cập nhật imageUrl; ngược lại, giữ nguyên giá trị cũ
        if (welcomeDTO.getImageUrl() != null) {
            existingWelcome.setImageUrl(welcomeDTO.getImageUrl());
        }
        // Lưu đối tượng đã cập nhật vào cơ sở dữ liệu
        return welcomeRepository.save(existingWelcome);    }

    @Override
    public void deleteWelcome(Long welcomeId) {
        welcomeRepository.deleteById(welcomeId);
    }
}
