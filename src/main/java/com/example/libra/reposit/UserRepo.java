package com.example.libra.reposit;

import com.example.libra.domain.Role;
import com.example.libra.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllById (Long id);
    List<User> findAllByEmailContaining (String email);
    List<User> findAllByUsernameContaining( String username);
    List<User> findAllByRolesAndUsernameContaining(Role role,String username);
    List<User> findAllByRoles(Role role);

    Optional<User> findById(Long id);
}
