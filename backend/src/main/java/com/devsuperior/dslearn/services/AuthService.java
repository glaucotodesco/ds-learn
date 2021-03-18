package com.devsuperior.dslearn.services;

import java.util.Optional;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository repo;


    @Transactional(readOnly = true)
    public Optional<User> getAuthenticatedUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return repo.findByEmail(userEmail);
             
    }    
    
    public Optional<Boolean> validadeSelfOrAdmin(User userAuth, Long userId) {
        if(!userAuth.getId().equals(userId) && !userAuth.hasRole("ROLE_ADMIN")){
            return Optional.empty();
        }
        else{
            return Optional.of(true);
        }
    }

}
