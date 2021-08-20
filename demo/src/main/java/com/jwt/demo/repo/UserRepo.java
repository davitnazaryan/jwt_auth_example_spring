package com.jwt.demo.repo;

import com.jwt.demo.model.User;

import java.util.Optional;

public interface UserRepo {

  Optional<User> findById(String id);

  Optional<User> findByEmail(String id);

}
