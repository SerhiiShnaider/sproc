package com.gmail.shnapi007.config;

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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());

    return authenticationProvider;
  }

  public InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemory() {
    return new InMemoryUserDetailsManagerConfigurer<>();
  }

  @Autowired
  public void configureImMemory(AuthenticationManagerBuilder auth, AuthenticationProvider provider)
      throws Exception {
    inMemory()
        .withUser("adm")
        .password("adm")
        .authorities("ROLE_ADMIN")
        .and()
        .configure(auth);

    auth.authenticationProvider(provider);
 }

  @Bean
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
    userDetailsManager.createUser(
        User.withUsername("adm")
            .password("adm")
            .authorities("ADMIN")
            .build());
    return userDetailsManager;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .and()
        .formLogin()
        .loginPage("/header")
        .usernameParameter("xUserName")
        .passwordParameter("xUserPassword")
        .loginProcessingUrl("/login")
        .and()
        .rememberMe()
        .key("uniqueAndSecret")
        .rememberMeCookieName("onlm-remember-me")
        .tokenValiditySeconds(24 * 60 * 60)
        .and()
        .logout()
        .deleteCookies("ONLM_SESSION_ID")
        .permitAll()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .and()
        .csrf();  }
}
