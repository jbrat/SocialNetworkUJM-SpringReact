package com.socialNetwork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class for the security of applications with patterns URL, authentication
 * 
 * @author UJM's students
 */
@Configuration
@EnableWebSecurity
public class WebSecu extends WebSecurityConfigurerAdapter {
    
    /**
     * The user detail service to interact with current user
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Method to configure the principal routes of the application
     * 
     * @param http
     * 
     * @throws Exception 
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()       
            .antMatchers("/", "/facebook","/js/**", "/lib/**", "/images/**", "/fonts/**", "/css/**").permitAll()
            .antMatchers(HttpMethod.GET, "/register").permitAll()
            .antMatchers(HttpMethod.POST, "/register").permitAll()
            .antMatchers("/**").hasAnyAuthority("USER")
            .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .usernameParameter("email")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .deleteCookies("remember-me")
            .logoutSuccessUrl("/")
            .permitAll()
            .and()
            .rememberMe();
        ;
        http.csrf().disable();
    }

    /**
     * Use Bcrypt encryption for login form, encrypt the password to login 
     * 
     * @param auth
     * 
     * @throws Exception 
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
}
