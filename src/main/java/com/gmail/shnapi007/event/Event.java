package com.gmail.shnapi007.event;

import com.gmail.shnapi007.entity.User;
import com.google.common.base.MoreObjects;
import org.springframework.context.ApplicationEvent;

public abstract class Event extends ApplicationEvent {

  protected User user;

  public Event(User user) {
    super(user);

    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("user", user)
        .toString();
  }
}
