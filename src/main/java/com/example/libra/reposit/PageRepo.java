package com.example.libra.reposit;

import com.example.libra.domain.Book;
import com.example.libra.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PageRepo extends JpaRepository<Page, Long> {
    ArrayList<Page> findAllByIdBook(Book idBook);
    Optional<Page> findById(Long id);
    Page findFirstByIdBook(Book book);
    Page findByIdBookAndNumberChapterAndNumberPage(Book book,int chapter,int numberpage);


}
