package com.lynch.cars.gradledemo.model;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
class DriverTest {

  Driver driver = new Driver();

  @Test
  void getName() {
  driver.setName("john");
  assertEquals(driver.getName(), "john");
  }

  @Test
  void setName() {
    driver.setName("mike");
    assertEquals(driver.getName(), "mike");
  }
}
