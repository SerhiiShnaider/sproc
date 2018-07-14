package com.gmail.shnapi007.config;

import com.gmail.shnapi007.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("com.gmail.shnapi007")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  public InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemory() {
    return new InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>();
  }

  @Autowired
  public void configureInMemory(AuthenticationManagerBuilder auth)
      throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("user")
        .password("user")
        .roles("USER");
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/userList").permitAll()
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .and()
        .formLogin();
  }
}
