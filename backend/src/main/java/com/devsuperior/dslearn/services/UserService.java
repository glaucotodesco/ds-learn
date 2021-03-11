package com.devsuperior.dslearn.services;

import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  
    @Autowired
	private UserRepository repository;

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

}
