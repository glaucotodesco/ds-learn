package com.devsuperior.dslearn.components;

import java.util.HashMap;
import java.util.Map;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserRepository repo;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication auth) {
        
        User user = repo.findByEmail(auth.getName()).get();

        Map<String,Object> map = new HashMap<>();
        map.put("userName", user.getName());
        map.put("userId", user.getId());


        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(map);

        return accessToken;
    }
    
}
