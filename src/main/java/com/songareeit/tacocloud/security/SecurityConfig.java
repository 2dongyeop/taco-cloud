package com.songareeit.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(
                        authorizedUrl -> authorizedUrl
                                .requestMatchers(new AntPathRequestMatcher("/design")).hasRole("USER")
                                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                .httpBasic(Customizer.withDefaults())

                .build();
    }

    /* 인메모리 기반의 사용자 스토어  */
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user1")
//                .password("{noop}password1")
//                .roles("USER")
//                .build());
//
//        manager.createUser(User.withUsername("user2")
//                .password("{noop}password2")
//                .roles("USER")
//                .build());
//
//        return manager;
//    }
    /*  */

    /* JDBC 기반의 사용자 스토어  */
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users " +
                                "where username=?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities " +
                                "where username=?")
                .passwordEncoder(new NoEncodingPasswordEncoder());
    }
    /*  */


}
