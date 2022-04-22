package com.example.springsecuritymain;

import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.lang.reflect.Array;
import java.util.Arrays;


@Configuration
public class CustomSecurityConfigAdapter  extends WebSecurityConfigurerAdapter {


     // Authentication manager
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = passwordEncoder.encode("password");
//
//        // Authentication provider -- In memory
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails user = new User("sarath",password, Arrays.asList());
//        UserDetails user1 = User.withUsername("sarath1").password(password).authorities("read").build();
//        userDetailsManager.createUser(user);
//        userDetailsManager.createUser(user1);
//        auth.userDetailsService(userDetailsManager).passwordEncoder(new BCryptPasswordEncoder());
//
//    }

    @Autowired
    MyAuthenticationProvider provider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic();
        http.formLogin();
        //http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/hello").authenticated();
        http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);

    }

    @Bean
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder();
	}



}
