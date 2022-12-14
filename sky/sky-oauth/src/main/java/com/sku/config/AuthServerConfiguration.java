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
    //密码模式才需要配置,认证管理器
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
        defaultTokenServices.setRefreshTokenValiditySeconds(1500);
        return defaultTokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()") ///oauth/token_key公开
                .checkTokenAccess("permitAll()") ///oauth/check_token公开
                .allowFormAuthenticationForClients(); //允许表单认证
    }

    // 配置客户端
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // client的id和密码
                .withClient("client1")
                .secret(passwordEncoder.encode("123456"))

                // 给client一个id,这个在client的配置里要用的
                .resourceIds("resource1")

                // 允许的申请token的方式,测试用例在test项目里都有.
                // authorization_code授权码模式,这个是标准模式
                // implicit简单模式,这个主要是给无后台的纯前端项目用的
                // password密码模式,直接拿用户的账号密码授权,不安全
                // client_credentials客户端模式,用clientid和密码授权,和用户无关的授权方式
                // refresh_token使用有效的refresh_token去重新生成一个token,之前的会失效
                .authorizedGrantTypes("password", "authorization_code", "client_credentials", "implicit", "refresh_token")

                // 授权的范围,每个resource会设置自己的范围.
                .scopes("scope1")

                // 这个是设置要不要弹出确认授权页面的.
                .autoApprove(true)

                // 这个相当于是client的域名,重定向给code的时候会跳转这个域名
                .redirectUris("http://www.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager) //认证管理器
                .authorizationCodeServices(new InMemoryAuthorizationCodeServices()) //授权码管理
                .tokenServices(tokenServices()) //token管理
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

}
