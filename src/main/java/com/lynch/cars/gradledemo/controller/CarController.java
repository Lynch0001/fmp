package com.lynch.cars.gradledemo.controller;

import com.lynch.cars.gradledemo.model.Car;
import com.lynch.cars.gradledemo.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class CarController {

  private static Logger log = LoggerFactory.getLogger(CarController.class);

  @Autowired
  CarService carService;



  @GetMapping(value = "/cars/{id}")
  public String getAbout(@PathVariable Long id, Model model) {
    log.debug("Retrieving data for car id: {}", id);

    Optional<Car> carDto = carService.getCar(id);
    if (carDto.isPresent()){
      log.debug("Car retrieved: {}", carDto);
      model.addAttribute("car", carService.getCar(id).get());
    }
    return "cardetail";
  }

  @GetMapping(value = "/cars")
  public String getAllCars(Model model){
    model.addAttribute("cars", carService.getCars());
    model.addAttribute("listTitle", "Managed Vehicles");
    return "carlist";
  }

  @GetMapping(value = "/newcar")
  public String getNewCarForm(Model model){
    model.addAttribute("car", new Car());
    return "newcar_form";
  }

  @PostMapping(value = "/savenewcar", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String saveNewCar(Car car, Model model){
    Long newCarId = carService.saveCar(car);
    model.addAttribute("car", carService.getCar(newCarId).get());
    return "newcar_success";
  }

  @GetMapping(value = "/editcar/{id}")
  public String getEditCarForm(@PathVariable Long id, Model model){
    model.addAttribute("car", carService.getCar(id));
    return "editcar_form";
  }


  @PostMapping(value = "/saveeditcar", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String saveEditCar(Car car, Model model){
    carService.saveCar(car);
    model.addAttribute("id", car.getId());
    model.addAttribute("make", car.getMake());
    model.addAttribute("model", car.getModel());
    model.addAttribute("year", car.getManufactureYear());
    return "editcar_success";
  }

  @GetMapping(value = "/delete/{id}")
  public String deleteCar(@PathVariable Long id, Model model){
    carService.deleteCar(id);
    model.addAttribute("id", id);
    return "deletecar";
  }

  @GetMapping(value = "/outdatedservice")
    public String getCarsWithOutdatedService(Model model){
    List<Car> cars = carService.getServiceOutOfDate();
    model.addAttribute("cars", cars);
    model.addAttribute("listTitle", "Vehicle Service Overdue");
    return "carlist";
  }

}
