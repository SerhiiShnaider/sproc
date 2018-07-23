package com.gmail.shnapi007.service.Impl;

import com.gmail.shnapi007.dao.UserDao;
import com.gmail.shnapi007.dao.TokenDao;
import com.gmail.shnapi007.entity.User;
import com.gmail.shnapi007.entity.Token;
import com.gmail.shnapi007.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  @Autowired
  private UserDao userDao;

  @Override
  public void addUser(User user) {
    userDao.save(user);
  }

  @Override
  public void updateUser(User user) {
    userDao.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return userDao.findByUsername(userName);
  }


}
