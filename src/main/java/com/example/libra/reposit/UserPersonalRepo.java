package com.example.libra.reposit;

import com.example.libra.domain.User;
import com.example.libra.domain.UserPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPersonalRepo extends JpaRepository<UserPersonal, Long> {
    List<UserPersonal> findAllById(Long id);
}
