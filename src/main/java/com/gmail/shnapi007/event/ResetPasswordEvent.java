package com.gmail.shnapi007.event;

import com.gmail.shnapi007.entity.User;

public class ResetPasswordEvent extends Event {

  public ResetPasswordEvent(User user) {
    super(user);
  }
}
