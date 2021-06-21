package com.example.libra.servise;


import com.example.libra.domain.Book;
import com.example.libra.domain.Genre;
import com.example.libra.reposit.BookRepo;
import com.example.libra.reposit.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServise {

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private BookRepo bookRepo;

    public List<Genre> findAll() {
        return genreRepo.findAll();
    }

    public Genre findByIdGenre(String idGenre) {
        Genre genre=new Genre();
        if(!idGenre.equals(""))
        if(genreRepo.findById(Long.parseLong(idGenre)).isPresent())
            genre=genreRepo.findById(Long.parseLong(idGenre)).get();
        else {
            genre.setIdGenre((long) 0);
            genre.setNameGenre("Любой жанр");
        }
        else {
            genre.setIdGenre((long) 0);
            genre.setNameGenre("Любой жанр");
        }
        return genre;
    }


    public List<Genre> findTop8Genres() {
        List<Genre> genres=genreRepo.findAll();
        List<Genre> genres1 = new ArrayList<>();
        int i=0;
        for(Genre genre:genres){
            genres1.add(genre);
            if(i==7)
                break;
            i++;
        }
        return genres1;
    }

    public void updateGenre(String nameNewGenre, String idGenreDel, String idGenreUpdate, String nameGenre) {
        if(!idGenreDel.isEmpty()){
            Genre findGenre=genreRepo.findByIdGenre(Long.parseLong(idGenreDel)).get();
            List<Book> books= bookRepo.findAllByGenres(findGenre);
            for (Book book:books){
                List<Genre> genres= book.getGenres();
                genres.removeIf(genre -> genre.equals(findGenre));
            }
            bookRepo.saveAll(books);
            genreRepo.delete(genreRepo.findByIdGenre(Long.parseLong(idGenreDel)).get());
        }
        if(!nameNewGenre.isEmpty()){
            Genre genre=new Genre();
            genre.setNameGenre(nameNewGenre);
            genreRepo.save(genre);
        }
        if(!idGenreUpdate.isEmpty()){
            Genre genre=genreRepo.findByIdGenre(Long.parseLong(idGenreUpdate)).get();
            genre.setNameGenre(nameGenre);
            genreRepo.save(genre);
        }
    }
}
