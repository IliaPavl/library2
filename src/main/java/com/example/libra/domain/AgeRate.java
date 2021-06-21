package com.example.libra.domain;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "age_rate")
public class AgeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String ageRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgeRate() {
        return ageRate;
    }

    public void setAgeRate(String ageRate) {
        this.ageRate = ageRate;
    }
}
