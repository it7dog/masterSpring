package com.smart.cache.simplecache;

import com.smart.cache.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service(value = "userServiceBean")
public class UserService {

  @Cacheable(cacheNames ="users")
  public User getUserById(String userId) {
    System.out.println("real query user."+userId);
    return getFromDB(userId);
  }

  private User getFromDB(String userId) {
    System.out.println("real querying db ..." + userId);
    return new User(userId);
  }
}
