package com.gmail.shnapi007.controller;

import com.gmail.shnapi007.service.TokenService;
import com.gmail.shnapi007.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class MainController {

  @Autowired
  protected UserService userService;

  @Autowired
  protected TokenService tokenService;

  @Autowired
  protected PasswordEncoder passwordEncoder;

  @Autowired
  protected ApplicationEventPublisher eventPublisher;
}
