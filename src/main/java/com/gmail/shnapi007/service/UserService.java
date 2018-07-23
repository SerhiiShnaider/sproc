package com.gmail.shnapi007.service;

import com.gmail.shnapi007.entity.User;
import com.gmail.shnapi007.entity.Token;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

  void addUser(User user);

  void updateUser(User user);

  UserDetails loadUserByUsername(String userName);
}
