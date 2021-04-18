package com.enset.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());


        //auth.authenticationProvider(authProvider());

//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username as principal,password as credentials,active from users where username=?")
//                .authoritiesByUsernameQuery("select username as principal,role as role from users_roles where username=?")
//                .passwordEncoder(passwordEncoder)
//                .rolePrefix("ROLE_");

//        auth.inMemoryAuthentication().withUser("user1").password(pass).roles("USER");
//        auth.inMemoryAuthentication().withUser("user2").password(pass).roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password(pass).roles("USER","ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.authorizeRequests().antMatchers("/savePatient**/**","/deletPatient**/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("index**/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.csrf();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
