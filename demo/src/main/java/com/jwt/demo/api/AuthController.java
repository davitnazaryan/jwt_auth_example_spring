package com.jwt.demo.api;

import com.jwt.demo.model.User;
import com.jwt.demo.repo.UserRepo;
import com.jwt.demo.service.TokenGeneratorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

  private final UserRepo repo;
  private final TokenGeneratorService tokenGeneratorService;

  public AuthController(UserRepo repo, TokenGeneratorService tokenGeneratorService) {
    this.repo = repo;
    this.tokenGeneratorService = tokenGeneratorService;
  }

  @PostMapping("/auth/login")
  public Map<String, String> login(@RequestBody LoginPayload payload) {
    User user = this.repo.findByEmail(payload.getEmail())
        .orElseThrow(() -> new RuntimeException("No user found"));

    //todo 1. get payload password encrypt then compare with existing user encrypted password
    if (!user.getPassword().equals(payload.password)) {
      throw new RuntimeException("No user found");
    }
    Map<String, String> resp = new HashMap<>();
    resp.put("access_token", tokenGeneratorService.generate(user));
    return resp;
  }


  private static class LoginPayload {
    String email;
    String password;

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
}
