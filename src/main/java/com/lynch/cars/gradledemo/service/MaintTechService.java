package com.lynch.cars.gradledemo.service;

import com.lynch.cars.gradledemo.model.MaintTech;
import com.lynch.cars.gradledemo.repo.MaintTechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintTechService {

  @Autowired
  private MaintTechRepository maintTechRepository;

  public List<MaintTech> getMaintTechs(){
    return maintTechRepository.findAll();
  }
}
