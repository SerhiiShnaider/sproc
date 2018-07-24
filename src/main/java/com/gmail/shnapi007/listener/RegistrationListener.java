package com.gmail.shnapi007.listener;

import com.gmail.shnapi007.entity.User;
import com.gmail.shnapi007.event.RegistrationCompleteEvent;
import java.util.UUID;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener extends Listener implements
    ApplicationListener<RegistrationCompleteEvent> {

  @Override
  public void onApplicationEvent(RegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  private void confirmRegistration(RegistrationCompleteEvent event) {
    User user = event.getUser();
    String token = UUID.randomUUID().toString() + UUID.randomUUID().toString();
    tokenService.createToken(user, token);

    String recipientAddress = user.getEmail();
    String subject = "Registration Confirmation";
    String url = "/registrationConfirm.html?token=" + token;
    String message = "Url: ";

    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(recipientAddress);
    email.setSubject(subject);
    email.setText(message + env.getProperty("app.host") + env.getProperty("server.port") + url);
    mailSender.send(email);
  }
}
