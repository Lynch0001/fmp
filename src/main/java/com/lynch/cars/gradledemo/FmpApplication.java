package com.lynch.cars.gradledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * The GradledemoApplication is a program to manage a notional vehicle
 * fleet and explore Spring MVC, Thymeleaf and Gradle.
 *
 * @author  Tim Lynch
 * @version 1.0
 * @since   2020-12-25
 */

@SpringBootApplication
public class FmpApplication {

  public static void main(String[] args) {
    SpringApplication.run(FmpApplication.class, args);
  }

}
