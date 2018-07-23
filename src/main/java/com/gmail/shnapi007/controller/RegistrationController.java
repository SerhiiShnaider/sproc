package com.gmail.shnapi007.controller;

import com.gmail.shnapi007.entity.User;
import com.gmail.shnapi007.entity.Token;
import com.gmail.shnapi007.event.RegistrationCompleteEvent;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RegistrationController extends MainController {

  @Value("${error.message}")
  private String errorMessage;

  @GetMapping(value = {"/registration"})
  public String showAddPersonPage(Model model) {

    User user = new User();
    model.addAttribute("userForm", user);

    return "registration";
  }

  @PostMapping(value = {"/registration"})
  public String addUser(Model model, @RequestParam String username, @RequestParam String password,
      @RequestParam String email, WebRequest request) {
    if (username != null && username.length() > 0 && password != null && password.length() > 0 &&
        email != null && email.length() > 0) {

      User user = new User(username, passwordEncoder.encode(password), email);
      userService.addUser(user);
      eventPublisher.publishEvent(new RegistrationCompleteEvent(user));

      return "redirect:/";
    }

    model.addAttribute("errorMessage", errorMessage);
    return "registration";
  }

  @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
  public String confirmRegistration(Model model, @RequestParam("token") String token) {

    Token registrationToken = tokenService.getToken(token);
    if (registrationToken == null) {
      String message = "Invalid token";
      model.addAttribute("message", message);
      return "redirect:/badUser";
    }

    User user = registrationToken.getUser();
    LocalDateTime now = LocalDateTime.now();

    if (registrationToken.isWasUsed()) {
      String messageValue = "Token was used";
      model.addAttribute("message", messageValue);
      return "redirect:/badUser";
    }

    if (registrationToken.getExpiryDate().isBefore(now)) {
      String messageValue = "Token expired";
      model.addAttribute("message", messageValue);
      return "redirect:/badUser";
    }

    user.setEnabled(true);
    registrationToken.setWasUsed(true);
    userService.updateUser(user);
    return "redirect:/";
  }
}
