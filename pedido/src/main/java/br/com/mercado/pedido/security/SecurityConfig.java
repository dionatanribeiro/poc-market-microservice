package br.com.mercado.pedido.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class SecurityConfig extends ResourceServerConfigurerAdapter {

    // Template para especificar quais urls devem possuir acesso
    // publico
    private static final String[] PUBLIC_MATCHERS = {
        "/hello"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS)
                .permitAll()
                .anyRequest()
                .authenticated();
    }

}
