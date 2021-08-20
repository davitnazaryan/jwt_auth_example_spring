package com.jwt.demo.api;

import com.jwt.demo.model.User;
import com.jwt.demo.repo.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

  private final UserRepo repo;

  public UserController(UserRepo repo) {
    this.repo = repo;
  }

  @GetMapping("/users/current")
  public User login(HttpServletRequest request) {
    return this.repo.findById(request.getAttribute("USER_ID").toString()).orElse(new User());
  }

}
