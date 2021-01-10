package com.lynch.cars.gradledemo.config;

import com.lynch.cars.gradledemo.model.Role;
import com.lynch.cars.gradledemo.model.User;
import com.lynch.cars.gradledemo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {

    userRepository.deleteAll();

    User user1 = new User();
    user1.setUsername("user");
    user1.setPassword(passwordEncoder.encode("password"));
    user1.setAccountNonExpired(true);
    user1.setAccountNonLocked(true);
    user1.setCredentialsNonExpired(true);
    user1.setEnabled(true);
    user1.grantAuthority(Role.ROLE_USER);
    userRepository.save(user1);

    User user2 = new User();
    user2.setUsername("admin");
    user2.setPassword(passwordEncoder.encode("password"));
    user2.grantAuthority(Role.ROLE_ADMIN);
    user2.setAccountNonExpired(true);
    user2.setAccountNonLocked(true);
    user2.setCredentialsNonExpired(true);
    user2.setEnabled(true);
    userRepository.save(user2);

  }
}
