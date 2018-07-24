package com.gmail.shnapi007.service;

import com.gmail.shnapi007.entity.Token;
import com.gmail.shnapi007.entity.User;

public interface TokenService {

  void createToken(User user, String token);

  Token getToken(String VerificationToken);

  Token getToken(User user);

  void updateToken(Token Token);

}
