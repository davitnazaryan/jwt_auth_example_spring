package com.jwt.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;

@Service
public class TokenValidatorServiceImpl implements TokenValidatorService {

  @Override
  public Claims validate(String token) {
    String[] tokenParts = token.split(" ");
    if (tokenParts.length != 2) {
      throw new RuntimeException("Invalid header must be `Bearer token`");
    }
    if (!tokenParts[0].equals("Bearer")) {
      throw new RuntimeException("Invalid header must be `Bearer token`");
    }
    String str = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEG" +
        "hXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMg" +
        "PA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2Xw" +
        "lSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(DatatypeConverter.parseBase64Binary(str))
        .build()
        .parseClaimsJws(tokenParts[1])
        .getBody();


    return claims;
  }
}
