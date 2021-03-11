package com.devsuperior.dslearn.config;

import java.util.Arrays;

import com.devsuperior.dslearn.components.JwtTokenEnhancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private JwtTokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${jwt.duration}")
    private Integer jwtDuration;

    @Autowired
    private JwtTokenEnhancer tokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        TokenEnhancerChain chain = new TokenEnhancerChain();
        chain.setTokenEnhancers(Arrays.asList(accessTokenConverter, tokenEnhancer));


        //@formatter:off
        endpoints.authenticationManager(authenticationManager)
                 .tokenStore(tokenStore)      
                 .accessTokenConverter(accessTokenConverter)
                 .tokenEnhancer(chain);
        //@formatter:on
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        
        //@formatter:off
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
        //@formatter:on
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        
        //@formatter:off
        clients.inMemory()
               .withClient(clientId)
               .secret(passwordEncoder.encode(clientSecret))
               .scopes("read","write")
               .authorizedGrantTypes("password")
               .accessTokenValiditySeconds(jwtDuration);
        //@formatter:on
    }

}
