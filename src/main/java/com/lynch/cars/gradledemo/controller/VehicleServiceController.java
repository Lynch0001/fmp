package com.lynch.cars.gradledemo.controller;

import com.lynch.cars.gradledemo.model.Car;
import com.lynch.cars.gradledemo.model.VehicleService;
import com.lynch.cars.gradledemo.service.CarService;
import com.lynch.cars.gradledemo.service.VehicleServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class VehicleServiceController {

  private static Logger log = LoggerFactory.getLogger(VehicleServiceController.class);

  @Autowired
  private VehicleServiceService vehicleServiceService;

  @Autowired
  private CarService carService;

  @GetMapping(value = "cars/{id}/servicein")
  public String provideServiceInForm(@PathVariable Long id, Model model) throws Exception{
    if(!vehicleServiceService.vehicleAvailableForService(id)){
      throw new Exception("Vehicle Not Available for Service");
    }
    Car car = carService.getCar(id).get();
    model.addAttribute("car", car);
    model.addAttribute("vehicleService", new VehicleService());
    return "servicein_form";
  }

  @PostMapping(value = "/cars/{id}/saveservicein", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String processServiceInForm(@PathVariable Long id,
                                     @ModelAttribute("car") Car car,
                                     VehicleService vehicleService,
                                     Model model) throws Exception {
    log.debug("VS CONTROLLER - received service {}", vehicleService);
    Car updatedCar = vehicleServiceService.updateVehicleWithNewService(id, vehicleService);
    model.addAttribute("vehicleService", vehicleService);
    model.addAttribute("car", updatedCar);
    return "servicein_success";
  }

  @GetMapping(value = "/cars/{id}/serviceout")
  public String provideServiceOutForm(@PathVariable Long id, Model model) throws Exception {
    if(!carService.existsCarsById(id)){
      throw new Exception("Vehicle Not Found");}
    Car car = carService.getCar(id).get();
    if(!vehicleServiceService.vehicleIsInService(id)){
      throw new Exception("Vehicle is Not in Maintenance Service");}
    VehicleService activeVehicleService = vehicleServiceService.findActiveServiceForVehicle(car);
    log.debug("DISPATCH CONTROLLER - id for active dispatch returned {}", activeVehicleService.getId());
    model.addAttribute("car", car);
    model.addAttribute("service", activeVehicleService);
    return "serviceout_form";
  }

  @PostMapping(value = "/cars/{id}/saveserviceout", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String processServiceOut(@PathVariable Long id,
                                  @ModelAttribute("car") Car car,
                                  VehicleService vehicleService,
                                  Model model) throws Exception {
    log.debug("DISPATCH CONTROLLER - received Vehicle Service Return {}", vehicleService);
    Car updatedCar = vehicleServiceService.updateVehicleWithReturnFromService(id, vehicleService);
    model.addAttribute("car", updatedCar);
    return "serviceout_success";
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView errorHandler(HttpServletRequest request, Exception exception){
    ModelAndView mav = new ModelAndView();
    mav.addObject(exception);
    mav.setViewName("error");
    return mav;
  }
}
