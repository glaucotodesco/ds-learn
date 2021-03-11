package com.devsuperior.dslearn.repositories;


import java.util.Optional;
import com.devsuperior.dslearn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
