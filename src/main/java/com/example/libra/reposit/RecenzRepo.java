package com.example.libra.reposit;

import com.example.libra.domain.Book;
import com.example.libra.domain.Recenz;
import com.example.libra.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecenzRepo extends JpaRepository<Recenz, Long> {
    List<Recenz> findAllByBookid(Book book);
    Recenz findByAuthorAndBookid(User user, Book book);
    List<Recenz> findAllByAuthor(User user);
}
