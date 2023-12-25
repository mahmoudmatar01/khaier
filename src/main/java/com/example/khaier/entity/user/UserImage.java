package com.example.khaier.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_image")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private byte[] data;
    private String title;
    private String url;
}
