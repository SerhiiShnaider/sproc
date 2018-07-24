package com.gmail.shnapi007.controller;

import com.gmail.shnapi007.entity.Token;
import com.gmail.shnapi007.entity.User;
import com.gmail.shnapi007.event.RegistrationCompleteEvent;
import com.gmail.shnapi007.event.ResetPasswordEvent;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResetPasswordController extends MainController {

  @GetMapping(value = {"/forgotPassword"})
  public String forgotPasswordPage(Model model) {

    return "forgotPassword";
  }

  @PostMapping(value = {"/forgotPassword"})
  public String forgotPasswordPage(Model model, @RequestParam String username) {

    if (username != null && username.length() > 0) {

      User user = (User) userService.loadUserByUsername(username);
      if (user != null) {
        eventPublisher.publishEvent(new ResetPasswordEvent(user));
      }
    }
    return "redirect:/";
  }

  @GetMapping(value = {"/resetPassword"})
  public String resetPassword(Model model, @RequestParam("token") String token) {

    Token registrationToken = tokenService.getToken(token);
    LocalDateTime now = LocalDateTime.now();

    if (registrationToken == null) {
      String message = "Invalid token";
      model.addAttribute("message", message);
      return "redirect:/badUser";
    }

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
    model.addAttribute("resetToken", token);
    return "resetPassword";
  }

  @PostMapping(value = {"/resetPassword"})
  public String resetPassword(Model model, @RequestParam String password,
      @RequestParam String password1, @RequestParam("token") String tokenValue) {

    if (password.equals(password1)) {
      Token token = tokenService.getToken(tokenValue);
      User user = token.getUser();
      user.setPassword(passwordEncoder.encode(password));
      userService.updateUser(user);
      token.setWasUsed(true);
      tokenService.updateToken(token);
      return "redirect:/";
    }

    model.addAttribute("resetToken", tokenValue);
    model.addAttribute("errorMessage", "Passwords must coincide");
    return "resetPassword";
  }
}
