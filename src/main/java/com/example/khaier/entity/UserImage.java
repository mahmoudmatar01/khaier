package com.example.khaier.entity;

import com.example.khaier.utils.shared.ImageData;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_image")
@AllArgsConstructor
public class UserImage  extends ImageData {
}
