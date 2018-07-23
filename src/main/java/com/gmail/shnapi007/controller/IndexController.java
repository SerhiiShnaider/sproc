package com.gmail.shnapi007.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends MainController {

  // Inject via application.properties
  @Value("${welcome.message}")
  private String message;

  @GetMapping(value = {"/", "/index"})
  public String index(Model model) {

    model.addAttribute("message", message);
    return "index";
  }

  @GetMapping(value = "badUser")
  public String badUser(Model model) {
    return "badUser";
  }
}
