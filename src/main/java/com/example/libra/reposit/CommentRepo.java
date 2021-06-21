package com.example.libra.reposit;

import com.example.libra.domain.Book;
import com.example.libra.domain.Coment;
import com.example.libra.domain.Page;
import com.example.libra.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CommentRepo extends JpaRepository<Coment, Long> {
    ArrayList<Coment> findAllByPage(Page page);
    List<Coment> findAllByBook(Book book);
    List<Coment> findAllByAuthor(User user);
}
