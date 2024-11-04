package com.huynhminhkhai.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WelcomeDTO {

    private String title;

    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    private MultipartFile file;
}
