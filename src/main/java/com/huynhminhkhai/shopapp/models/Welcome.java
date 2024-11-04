package com.huynhminhkhai.shopapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "welcomes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Welcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;
}
