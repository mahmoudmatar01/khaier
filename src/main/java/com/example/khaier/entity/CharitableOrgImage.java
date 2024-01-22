package com.example.khaier.entity;

import com.example.khaier.utils.shared.ImageData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "charitable_organizations-image")
@AllArgsConstructor
public class CharitableOrgImage extends ImageData {

}
