package com.gmail.shnapi007.listener;

import com.gmail.shnapi007.entity.Token;
import com.gmail.shnapi007.entity.User;
import com.gmail.shnapi007.event.ResetPasswordEvent;
import java.util.UUID;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class ResetPasswordListener extends Listener implements
    ApplicationListener<ResetPasswordEvent> {

  @Override
  public void onApplicationEvent(ResetPasswordEvent resetPasswordEvent) {
    this.resetPassword(resetPasswordEvent);
  }

  private void resetPassword(ResetPasswordEvent event) {
    User user = event.getUser();
    String key = UUID.randomUUID().toString() + UUID.randomUUID().toString();

    Token token = tokenService.getTokenByUser(user);
    updateToken(token, key);

    String recipientAddress = user.getEmail();
    String subject = "Reset password";
    String url = "/resetPassword.html?token=" + key;
    String message = "Url: ";

    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(recipientAddress);
    email.setSubject(subject);
    email.setText(message + env.getProperty("app.host") + env.getProperty("server.port") + url);
    mailSender.send(email);
  }

  public void updateToken(Token token, String key) {
    token.setWasUsed(false);
    token.setToken(key);
    tokenService.updateToken(token);
  }
}
