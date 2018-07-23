package com.gmail.shnapi007.event;

import com.gmail.shnapi007.entity.User;

public class RegistrationCompleteEvent extends Event {

  public RegistrationCompleteEvent(User user) {
    super(user);
  }
}
