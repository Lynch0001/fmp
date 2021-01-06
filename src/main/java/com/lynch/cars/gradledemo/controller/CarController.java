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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Controller
public class CarController {

  private static Logger log = LoggerFactory.getLogger(CarController.class);

  @Autowired
  CarService carService;

  @GetMapping(value = "/cars/{id}")
  public String getCar(@PathVariable Long id, Model model) {
    log.debug("Retrieving data for car id: {}", id);
    Optional<Car> carDto = carService.getCar(id);
    if (carDto.isPresent()){
      log.debug("Car retrieved: {}", carDto);
      model.addAttribute("car", carService.getCar(id).get());
    }
    log.debug("CAR CONTROLLER - dispatches for vehicle id {}: {}", id, carDto.get().getDispatches());
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

  @GetMapping(value = "/cars/{id}/editcar")
  public String getEditCarForm(@PathVariable Long id, Model model){
    model.addAttribute("id", id);
    model.addAttribute("car", carService.getCar(id));
    return "editcar_form";
  }

  @PostMapping(value = "/cars/{id}/saveeditcar", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String saveEditCar(@PathVariable Long id, Car car, Model model) throws Exception {
    Car updatedCar = carService.updateCar(id, car);
    model.addAttribute("car", updatedCar);
    return "editcar_success";
  }

  @GetMapping(value = "/delete/{id}")
  public String deleteCar(@PathVariable Long id, Model model){
    carService.deleteCar(id);
    model.addAttribute("id", id);
    return "deletecar";
  }

  @GetMapping(value = "/cars/outdatedservice")
    public String getExpiredServicesList(Model model){
    List<Car> cars = carService.getServiceOutOfDate();
    model.addAttribute("cars", cars);
    model.addAttribute("listTitle", "Vehicle Service Overdue");
    return "carlist";
  }

  @GetMapping(value = "/cars/pendingservice")
  public String getPendingServicesList(Model model){
    List<Car> cars = carService.getPendingServiceVehicleList();
    model.addAttribute("cars", cars);
    model.addAttribute("listTitle", "Vehicle Service Pending - Next 90 Days");
    return "carlist";
  }

  @GetMapping(value = "/cars/available")
  public String getAvailableVehicleList(Model model){
    List<Car> cars = carService.getAllAvailableVehicles();
    model.addAttribute("cars", cars);
    model.addAttribute("listTitle", "Available Vehicle Listing");
    return "carlist";
  }

  @GetMapping(value = "/cars/notavailable")
  public String getNonAvailableVehicleList(Model model){
    List<Car> cars = carService.getNonAvailableVehicles();
    model.addAttribute("cars", cars);
    model.addAttribute("listTitle", "Non-Available Vehicle Listing");
    return "carlist";
  }

  @GetMapping(value = "/cars/dispatched")
  public String getDispatchedVehicleList(Model model){
    List<Car> cars = carService.getAllDispatchedVehicles();
    model.addAttribute("cars", cars);
    model.addAttribute("listTitle", "Dispatched Vehicle Listing");
    return "carlist";
  }


  @ExceptionHandler(Exception.class)
  public ModelAndView errorHandler(HttpServletRequest request, Exception exception){
    ModelAndView mav = new ModelAndView();
    mav.addObject(exception);
    mav.setViewName("error");
    return mav;
  }

}
