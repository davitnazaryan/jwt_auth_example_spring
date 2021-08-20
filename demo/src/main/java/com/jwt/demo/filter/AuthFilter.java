package com.jwt.demo.filter;

import com.jwt.demo.service.TokenValidatorService;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends HttpFilter {

  private final TokenValidatorService validatorService;

  public AuthFilter(TokenValidatorService validatorService) {
    this.validatorService = validatorService;
  }

  @Override
  protected void doFilter(HttpServletRequest req,
                          HttpServletResponse resp,
                          FilterChain chain) throws IOException, ServletException {
    String authHeader = req.getHeader("Authorization");
    if (authHeader == null) {
      resp.setStatus(401);
      return;
    }
    Claims claims = this.validatorService.validate(authHeader);
    req.setAttribute("USER_ID", claims.getId());
    chain.doFilter(req, resp);
  }
}
