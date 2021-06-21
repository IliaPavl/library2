package com.example.libra.reposit;

import com.example.libra.domain.User;
import com.example.libra.domain.UserPropertyPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPropertyRepo extends JpaRepository<UserPropertyPage, Long> {
    UserPropertyPage findByUser(User user);
}
