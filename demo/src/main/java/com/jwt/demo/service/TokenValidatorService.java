package com.jwt.demo.service;

import io.jsonwebtoken.Claims;

public interface TokenValidatorService {

  Claims validate(String token);

}
