package com.example.libra.reposit;

import com.example.libra.domain.Subs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface SubsRepo extends JpaRepository<Subs, Long> {
    ArrayList<Subs> findAllByIdWhoSub(Long whoSub);
    ArrayList<Subs> findAllByIdAuthor(Long whoSub);
    Subs findByIdWhoSubAndIdAuthor(Long whoSub,Long author);
    ArrayList<Subs> findAllById(Long id);
    Optional<Subs> findByIdWhoSub(Long whoSub);
    Optional<Subs> findByIdAuthorAndIdWhoSub(Long idAuthor,Long whoSub);

}
