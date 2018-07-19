package com.gmail.shnapi007.controller;

import com.gmail.shnapi007.entity.User;
import com.gmail.shnapi007.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // Inject via application.properties
  @Value("${welcome.message}")
  private String message;

  @Value("${error.message}")
  private String errorMessage;

  @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
  public String index(Model model) {

    model.addAttribute("message", message);
    return "index";
  }

  @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
  public String showAddPersonPage(Model model) {

    User user = new User();
    model.addAttribute("userForm", user);

    return "addUser";
  }

  @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
  public String addUser(Model model, @RequestParam String username, @RequestParam String password) {

    if (username != null && username.length() > 0 && password != null && password.length() > 0) {
      userService.addUser(new User(username, passwordEncoder.encode(password)));
      return "redirect:/xxx";
    }

    model.addAttribute("errorMessage", errorMessage);
    return "addUser";
  }

  @GetMapping("/xxx")
  public String login() {
    return "login";
  }
}
