package com.example.libra.reposit;

import com.example.libra.domain.ComentToUser;
import com.example.libra.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ComentToUserRepo extends JpaRepository<ComentToUser, Long> {
    ArrayList<ComentToUser> findAllByAuthor(User author);
    ArrayList<ComentToUser> findAllByPageUser(User user);
    List<ComentToUser> findAllByPageUserAndPersonalAndIsitnew(User user, boolean person,boolean isitnew);
    List<ComentToUser> findAllByAuthorAndPersonalAndIsitnew(User user, boolean person,boolean isitnew);

    ArrayList<ComentToUser> findAllByAuthorAndPersonalAndPageUser(User author,boolean personal,User userPage);
    ComentToUser findFirstByPageUser(User user);

    ArrayList<ComentToUser> findAllByPageUserAndPersonal(User user,boolean isPersonal);
    ArrayList<ComentToUser> findAllByAuthorAndPersonal(User user,boolean isPersonal);
}
