package com.zcyang.auth.config;

import com.zcyang.auth.component.JwtTokenEnhancer;
import com.zcyang.auth.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private TokenStore redisTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    /**
     * 使用密码模式需要配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer); //配置JWT的内容增强器
        delegates.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(delegates);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userLoginService)
                .tokenStore(redisTokenStore) //配置令牌存储策略
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(enhancerChain);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /**
         * 配置客户端clientid secret等信息
         */
        clients.inMemory() // 使用in-memory存储
                .withClient("admin")//client_id用来标识客户的Id
                .secret(passwordEncoder.encode("admin_123"))//secret客户端安全码
                .scopes("all") //允许授权范围--这一项用于服务提供商区分提供哪些服务数据
                //.authorities("ROLE_ADMIN","ROLE_USER")//客户端可以使用的权限
                .redirectUris("http://www.baidu.com")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")//允许授权类型
                .accessTokenValiditySeconds(3600) //token过期时间
                .refreshTokenValiditySeconds(3600); //refresh过期时间

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("isAuthenticated()")// 开启/oauth/token_key验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")// 开启/oauth/check_token验证端口认证权限访问
                .allowFormAuthenticationForClients();//允许表单认证
    }
}
