package com.gmail.shnapi007.controller;

import com.gmail.shnapi007.entity.User;
import com.gmail.shnapi007.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

  @Autowired
  private UserService userService;

  private static List<User> users = new ArrayList<>();

  static {
    users.add(new User("Bill", "Gates"));
    users.add(new User("Steve", "Jobs"));
  }

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

  @RequestMapping(value = {"/userList"}, method = RequestMethod.GET)
  public String userList(Model model) {

    model.addAttribute("users", users);
    return "userList";
  }

  @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
  public String showAddPersonPage(Model model) {

    User user = new User();
    model.addAttribute("userForm", user);

    return "addUser";
  }

  @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
  public String addUser(Model model, @ModelAttribute("userForm") User user) {

    String username = user.getUsername();
    String userPassword = user.getPassword();

    if (username != null && username.length() > 0 && userPassword != null && userPassword.length() > 0) {

      userService.addUser(new User(username, userPassword));
      return "redirect:/userList";
    }

    model.addAttribute("errorMessage", errorMessage);
    return "addUser";
  }
}
