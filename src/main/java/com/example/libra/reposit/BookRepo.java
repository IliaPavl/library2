package com.example.libra.reposit;

import com.example.libra.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, Long> {
    ArrayList<Book> findByNameBookContaining(String namebook);
    ArrayList<Book> findAllByAgeRate(AgeRate idAgeRate);
    ArrayList<Book> findAllByStatus(Status idStatus);
    ArrayList<Book> findAllByGenres(Genre idGenres);
    ArrayList<Book> findAllByAgeRateAndStatus(AgeRate idAgeRate,Status isStatus);
    ArrayList<Book> findAllByAgeRateAndGenres(AgeRate idAgeRate,Genre idGenre);
    ArrayList<Book> findAllByStatusAndGenres(Status idStatus,Genre idGenre);
    ArrayList<Book> findAllByStatusAndGenresAndAgeRate(Status idStatus,Genre idGenre,AgeRate agrate);
    ArrayList<Book> findAll();
    ArrayList<Book> findAllByAuthor(User user);
    Optional<Book> findById(Long idBook);
    ArrayList<Book> getTop20ByDateUpdateLessThanEqual(Date dateUpdate);
}
