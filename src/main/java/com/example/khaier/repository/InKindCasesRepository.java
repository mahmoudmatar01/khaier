package com.example.khaier.repository;

import com.example.khaier.entity.InKindCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InKindCasesRepository extends JpaRepository<InKindCase,Long> {
}
