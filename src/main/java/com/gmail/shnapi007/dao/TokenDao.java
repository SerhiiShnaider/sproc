package com.gmail.shnapi007.dao;

import com.gmail.shnapi007.entity.User;
import com.gmail.shnapi007.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenDao extends JpaRepository<Token, Long> {

  Token findByToken(String token);

  Token findByUser(User user);
}
