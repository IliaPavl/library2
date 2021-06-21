package com.example.libra.reposit;

import com.example.libra.domain.Book;
import com.example.libra.domain.LibraryUser;
import com.example.libra.domain.TypesLibUser;
import com.example.libra.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface LibraryUserRepo extends JpaRepository<LibraryUser, Long> {
    ArrayList<LibraryUser> findAllByUserAndNameLib(User user, TypesLibUser nameLib);

    Optional<LibraryUser> findByBookAndUser(Book book, User user);
    ArrayList<LibraryUser> findAllByBook(Book book);
    ArrayList<LibraryUser> findAllByUser(User user);
}
