package com.example.libra.servise;

import com.example.libra.domain.*;
import com.example.libra.reposit.BookRepo;
import com.example.libra.reposit.LibraryUserRepo;
import com.example.libra.reposit.TypesLibUserRepo;
import com.example.libra.reposit.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibUserService {
    @Autowired
    private LibraryUserRepo libraryUserRepo;

    @Autowired
    private TypesLibUserRepo typesLibUserRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    public List<TypesLibUser> findAllTypes(){
        return typesLibUserRepo.findAll();
    }

    public List<TypesBuf> getTypesAndBooksLendht(Long idUser){
        List<TypesLibUser> typesBuf=typesLibUserRepo.findAll();
        List<LibraryUser> libraryUsers;
        ArrayList<TypesBuf> typesBuf1=new ArrayList<>();
        TypesBuf typesBuf2;
        for(TypesLibUser typesLibUser:typesBuf) {
            libraryUsers = (libraryUserRepo.findAllByUserAndNameLib(userRepo.findById(idUser).get(), typesLibUser));
            typesBuf2=new TypesBuf(typesLibUser.getId(),typesLibUser.getNameType(),libraryUsers.size());
            typesBuf2.setIdType(typesLibUser.getId());
            typesBuf1.add(typesBuf2) ;
        }
        return typesBuf1;
    }

    public void addBook(String idBook, User user,Long idTypeLib,Long idUserLib) {
        if(!libraryUserRepo.findByBookAndUser(bookRepo.findById(Long.parseLong(idBook)).get(),user).isPresent()) {
            LibraryUser libraryUser = new LibraryUser();
            libraryUser.setBook(bookRepo.findById(Long.parseLong(idBook)).get());
            libraryUser.setUser(user);
            libraryUser.setNameLib(typesLibUserRepo.findById(idTypeLib).get());
            if (idUserLib != 0)
                libraryUser.setId(idTypeLib);
            libraryUserRepo.save(libraryUser);
        }
    }

    public List<Book> getBooksByType(Long idBook,Long idTtype) {
        ArrayList<LibraryUser> libraryUsers=libraryUserRepo.findAllByUserAndNameLib(userRepo.findById(idBook).get(),typesLibUserRepo.findById(idTtype).get());
        List<Book> books=new ArrayList<>();
        for(LibraryUser libraryUser:libraryUsers){
            books.add(libraryUser.getBook());
        }
        return books;
    }

    public TypesLibUser getNameTypeLib(Long idType){
        return typesLibUserRepo.findById(idType).get();
    }

    public void deliteBook(Long idBook, User user) {
        libraryUserRepo.delete(libraryUserRepo.findByBookAndUser(bookRepo.findById(idBook).get(),user).get());
    }

    public void moveBook(Long idBook, Long idType,User user) {
        LibraryUser libraryUser=libraryUserRepo.findByBookAndUser(bookRepo.findById(idBook).get(),user).get();
        libraryUser.setNameLib(typesLibUserRepo.findById(idType).get());
        libraryUserRepo.save(libraryUser);
    }
}
