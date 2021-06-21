package com.example.libra.reposit;

import com.example.libra.domain.Support;
import com.example.libra.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface SupportRepo extends JpaRepository<Support, Long> {
    ArrayList<Support> findAllByIsitnew(boolean isNew);
    ArrayList<Support> findAllByAuthor(User user);
    ArrayList<Support> findAllById (Long id);
}
