package org.multilinguals.example.infrastructure.config;

import org.multilinguals.example.infrastructure.security.RequestValidationFilter;
import org.multilinguals.example.query.user.UserDetailsViewRepository;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.Resource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsViewRepository userDetailsViewRepository;

    private static final String[] AUTH_WHITELIST = {
            "/user/sign-up-username",
            "/user/sign-in-with-password"
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().cacheControl().disable() //禁用缓存
                .and()
                .csrf().disable() // 基于token，不需要csrf
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 基于token，所以不需要session
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new RequestValidationFilter(authenticationManager(), userDetailsViewRepository));
    }
}
