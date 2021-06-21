package com.example.libra.reposit;

import com.example.libra.domain.Friends;
import com.example.libra.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FriendsRepo extends JpaRepository<Friends, Long> {
    ArrayList<Friends> findAllByWhoSubFriend(User whoSub);
    Friends findByAuthorFriendAndWhoSubFriend(User sub,User whoSub);
    ArrayList<Friends> findAllByAuthorFriend(User author);
}
