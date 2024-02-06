package com.example.khaier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "in_kind_cases")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class InKindCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @JsonIgnore
    private String includedItemName;

    @OneToMany(mappedBy = "inKindCase")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<InKindDonation> donations;

}
