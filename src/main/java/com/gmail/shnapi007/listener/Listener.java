package com.gmail.shnapi007.listener;

import com.gmail.shnapi007.service.TokenService;
import com.gmail.shnapi007.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class Listener {

  @Autowired
  protected UserService userService;

  @Autowired
  protected TokenService tokenService;

  @Autowired
  protected JavaMailSender mailSender;

  @Autowired
  protected Environment env;
}
