package com.jwt.demo.repo;

import com.jwt.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class UserRepoImpl implements UserRepo {

  private Map<String, User> users;

  @PostConstruct
  private void init() {
    users = IntStream.range(1, 10).mapToObj(i -> new User(
        i + "", i + "", i + "", i + ""))
        .collect(Collectors.toMap(User::getId, key -> key));
  }


  @Override
  public Optional<User> findById(String id) {
    return Optional.ofNullable(users.get(id));
  }

  @Override
  public Optional<User> findByEmail(String id) {
    return Optional.ofNullable(users.get(id));
  }
}
