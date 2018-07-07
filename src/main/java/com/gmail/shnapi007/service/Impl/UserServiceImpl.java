package com.gmail.shnapi007.service.Impl;

import com.gmail.shnapi007.service.UserService;
import com.gmail.shnapi007.dao.UserDao;
import com.gmail.shnapi007.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  //@Autowired
  private UserDao userDao;

  @Override
  public void addUser(User user) {
    //userDao.save(user);

  }
}
