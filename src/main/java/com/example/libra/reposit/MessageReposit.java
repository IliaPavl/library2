package com.example.libra.reposit;


import com.example.libra.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageReposit extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag);
}
