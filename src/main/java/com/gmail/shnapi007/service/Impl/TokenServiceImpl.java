package com.gmail.shnapi007.service.Impl;

import com.gmail.shnapi007.dao.TokenDao;
import com.gmail.shnapi007.entity.Token;
import com.gmail.shnapi007.entity.User;
import com.gmail.shnapi007.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

  @Autowired
  private TokenDao tokenDao;

  @Override
  public Token getToken(String token) {
    return tokenDao.findByToken(token);
  }

  @Override
  public void createToken(User user, String token) {
    Token myToken = new Token(token, user);
    tokenDao.save(myToken);
  }

  @Override
  public Token getTokenByUser(User user) {
    return tokenDao.findByUser(user);
  }

  @Override
  public void updateToken(Token Token) {
    tokenDao.save(Token);
  }

}
