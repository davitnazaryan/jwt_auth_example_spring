package com.jwt.demo.service;

import com.jwt.demo.model.User;

public interface TokenGeneratorService {

  String generate(User user);
}
