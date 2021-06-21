package com.example.libra.reposit;

import com.example.libra.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepo extends JpaRepository<Status, Long> {
    List<Status> findAllById (Long idStatus);

    Status findByNameStatus(String status);
}
