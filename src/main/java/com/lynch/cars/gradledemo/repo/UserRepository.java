package com.lynch.cars.gradledemo.repo;

import com.lynch.cars.gradledemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  public User findByUsername(String username);
}
