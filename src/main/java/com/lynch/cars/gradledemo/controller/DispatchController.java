package com.lynch.cars.gradledemo.controller;

import com.lynch.cars.gradledemo.model.Car;
import com.lynch.cars.gradledemo.model.Dispatch;
import com.lynch.cars.gradledemo.service.CarService;
import com.lynch.cars.gradledemo.service.DispatchService;
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
public class DispatchController {

  private static Logger log = LoggerFactory.getLogger(DispatchController.class);

  @Autowired
  private DispatchService dispatchService;

  @Autowired
  private CarService carService;

  @GetMapping(value = "/cars/{id}/dispatchout")
  public String provideDispatchOutForm(@PathVariable Long id, Model model) throws Exception {
    if(!dispatchService.vehicleAvailableForDispatch(id)){return "error";}
    Car car = carService.getCar(id).get();
    model.addAttribute("car", car);
    model.addAttribute("dispatch", new Dispatch());
    return "dispatchout_form";
  }

  @PostMapping(value = "/cars/{id}/savedispatchout", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String processDispatchOut(@PathVariable Long id,
                                   @ModelAttribute("car") Car car,
                                   Dispatch dispatch,
                                   Model model) {
    log.debug("DISPATCH CONTROLLER - received dispatch {}", dispatch);
    Car updatedCar = dispatchService.updateVehicleWithNewDispatch(id, dispatch);
    model.addAttribute("car", updatedCar);
    return "dispatchout_success";
  }

  @GetMapping(value = "/cars/{id}/dispatchin")
  public String provideDispatchInForm(@PathVariable Long id, Model model) throws Exception {
    if(!carService.existsCarsById(id)){
      throw new Exception("Vehicle Not Found");}
    Car car = carService.getCar(id).get();
    if(!dispatchService.vehicleIsDispatched(id)){
      throw new Exception("Vehicle is Not Dispatched");}
    Dispatch activeDispatch = dispatchService.findActiveDispatchForVehicle(car);
    log.debug("DISPATCH CONTROLLER - id for active dispatch returned {}", activeDispatch.getId());
    model.addAttribute("car", car);
    model.addAttribute("dispatch", activeDispatch);
    return "dispatchin_form";
  }

  @PostMapping(value = "/cars/{id}/savedispatchin", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String processDispatchIn(@PathVariable Long id,
                                   @ModelAttribute("car") Car car,
                                   Dispatch dispatch,
                                   Model model) {
    log.debug("DISPATCH CONTROLLER - received dispatch {}", dispatch);
    Car updatedCar = dispatchService.updateVehicleWithReturnDispatch(id, dispatch);
    model.addAttribute("car", updatedCar);
    return "dispatchin_success";
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView errorHandler(HttpServletRequest request, Exception exception){
    ModelAndView mav = new ModelAndView();
    mav.addObject(exception);
    mav.setViewName("error");
    return mav;
  }
}