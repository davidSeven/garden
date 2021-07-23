package com.sku.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author zhangyuyuan
 * @date 2021-07-22 16:25
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    protected TokenStore tokenStore;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setSupportRefreshToken(true);

        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setAccessTokenValiditySeconds(300);
        defaultTokenServices.setRefreshTokenValiditySeconds(300);
        return defaultTokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret(passwordEncoder.encode("123456"))
                .resourceIds("resource1")
                .authorizedGrantTypes("password", "authorization_code", "client_credentials", "implicit", "refresh_token")
                .scopes("scope1")
                .autoApprove(true)
                .authorities("admin")
                .redirectUris("http://www.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .authorizationCodeServices(new InMemoryAuthorizationCodeServices())
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

}
