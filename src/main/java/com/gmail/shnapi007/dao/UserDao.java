package com.gmail.shnapi007.dao;

import com.gmail.shnapi007.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

  User findByUsername(String userName);
}
