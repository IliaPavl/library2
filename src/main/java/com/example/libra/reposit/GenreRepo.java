package com.example.libra.reposit;

import com.example.libra.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepo extends JpaRepository<Genre, Long> {
    Genre findByNameGenre(String namegenre);
    List<Genre> findAllByIdGenre(Long id);
    Optional<Genre> findByIdGenre(Long id);
}
