package com.huynhminhkhai.shopapp.controllers;

import com.huynhminhkhai.shopapp.dtos.CategoryDTO;
import com.huynhminhkhai.shopapp.dtos.WelcomeDTO;
import com.huynhminhkhai.shopapp.models.Category;
import com.huynhminhkhai.shopapp.models.Welcome;
import com.huynhminhkhai.shopapp.repositories.WelcomeRepository;
import com.huynhminhkhai.shopapp.services.CategoryService;
import com.huynhminhkhai.shopapp.services.WelcomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/welcomes")
@RequiredArgsConstructor
public class WelcomeController {
    private final WelcomeService welcomeService;

    @GetMapping("image/{imageName}")
    public ResponseEntity<?> viewImage(@PathVariable String imageName) {
        try {
            java.nio.file.Path imagePath = Paths.get("Uploads/" + imageName);
            UrlResource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createWelcome(
            @Valid @ModelAttribute WelcomeDTO welcomeDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        try {
            MultipartFile file = welcomeDTO.getFile(); // Chỉ lấy một file ảnh
            if (file != null && file.getSize() > 0) {
                // Kiểm tra kích thước file
                if (file.getSize() > 10 * 1024 * 1024) { // nhỏ hơn 10MB
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File is too large! Maximum size is 10MB");
                }
                // Kiểm tra loại file
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("File must be an image");
                }
                welcomeDTO.setImageUrl(storeFile(file));
            }
            welcomeService.createWelcome(welcomeDTO);
            return ResponseEntity.ok("Create successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getWelcomeById(@PathVariable Long id) {
        try {
            Welcome welcome = welcomeService.getWelcomeById(id);
            return ResponseEntity.ok(welcome);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<Welcome>> getAllWelcome() {
        List<Welcome> welcomes = welcomeService.getAllWelcomes();
        return ResponseEntity.ok(welcomes);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateWelcome(
            @PathVariable Long id,
            @Valid @ModelAttribute WelcomeDTO welcomeDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }

        try {
            MultipartFile file = welcomeDTO.getFile();
            if (file != null && file.getSize() > 0) {
                // Kiểm tra kích thước file và loại file
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File is too large! Maximum size is 10MB");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("File must be an image");
                }

                // Cập nhật URL hình ảnh
                welcomeDTO.setImageUrl(storeFile(file));
            }
            Welcome welcome = welcomeService.updateWelcome(id, welcomeDTO);
            return ResponseEntity.ok(welcome);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWelcome(@PathVariable Long id) {
        try {
            welcomeService.deleteWelcome(id);
            return ResponseEntity.ok("Welcome deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    private String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        Path uploadDir = Paths.get("Uploads/");

        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        Path destination = uploadDir.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        // Trả về đường dẫn để lưu vào cơ sở dữ liệu
        return "/image/" + uniqueFileName; // Đảm bảo rằng bạn có thể truy cập ảnh qua URL này
    }

}
