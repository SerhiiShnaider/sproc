package com.gmail.shnapi007.controller;

import com.gmail.shnapi007.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

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
  public String savePerson(Model model, @ModelAttribute("userForm") User user) {

    String firstName = user.getFirstName();
    String lastName = user.getLastName();

    if (firstName != null && firstName.length() > 0 && lastName != null && lastName.length() > 0) {
      User newUser = new User(firstName, lastName);
      users.add(newUser);

      return "redirect:/userList";
    }

    model.addAttribute("errorMessage", errorMessage);
    return "addUser";
  }

}
