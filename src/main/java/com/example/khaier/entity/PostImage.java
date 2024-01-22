package com.example.khaier.entity;

import com.example.khaier.utils.shared.ImageData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "post_image")
@AllArgsConstructor
public class PostImage extends ImageData {

}
