package com.example.libra.reposit;

import com.example.libra.domain.AgeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgeRateRepo extends JpaRepository<AgeRate, Long> {
    List<AgeRate> findAllById(Long ageRate);

    AgeRate findByAgeRate(String ageRate);
}
