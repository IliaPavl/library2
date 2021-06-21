package com.example.libra.reposit;

import com.example.libra.domain.Book;
import com.example.libra.domain.Marks;
import com.example.libra.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarksRepo extends JpaRepository<Marks, Long> {
    Optional<Marks> findByUserAndBook(User user, Book book);
    List<Marks> findAllByBook(Book book);
    List<Marks> findAllByUser(User user);
}
