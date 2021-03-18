package com.devsuperior.dslearn.services;

import java.util.Optional;

import com.devsuperior.dslearn.dtos.UserDTO;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.repositories.UserRepository;
import com.devsuperior.dslearn.services.exceptions.EntityNotFoundException;
import com.devsuperior.dslearn.services.exceptions.ForbiddenException;
import com.devsuperior.dslearn.services.exceptions.UnauthorizedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //@formatter:off
		User user = repository
                    .findByEmail(username)
                    .orElseThrow( () -> {
                        return new UsernameNotFoundException("Email not found");
                    });
        //@formatter:on	  

        return user;
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {

        User userAuth = authService.getAuthenticatedUser().orElseThrow(() -> new UnauthorizedException("Invalid User"));

        authService.validadeSelfOrAdmin(userAuth, id).orElseThrow(() -> new ForbiddenException("Access denied"));

        if (!userAuth.getId().equals(id)) {
            return new UserDTO(userAuth);
        } else {
            Optional<User> op = repository.findById(id);

            User entity = op.orElseThrow(() -> new EntityNotFoundException("Entity Not Found " + id));
            return new UserDTO(entity);
        }

    }

}
