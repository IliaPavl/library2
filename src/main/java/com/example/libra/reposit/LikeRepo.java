package com.example.libra.reposit;

import com.example.libra.domain.Book;
import com.example.libra.domain.LikeBooks;
import com.example.libra.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepo extends JpaRepository<LikeBooks, Long> {
    Optional<LikeBooks> findByBookAndUser(Book book, User user);
    List<LikeBooks> findAllByBook(Book book);
    List<LikeBooks> findAllByUser(User user);
}
