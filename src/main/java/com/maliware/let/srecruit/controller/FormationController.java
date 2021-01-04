package com.maliware.let.srecruit.controller;

import com.maliware.let.srecruit.model.Cv;
import com.maliware.let.srecruit.model.Formation;
import com.maliware.let.srecruit.service.CvService;
import com.maliware.let.srecruit.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formation")
public class FormationController {
    @Autowired
    private FormationService formationService;

    @GetMapping
    ResponseEntity<List<Formation>> getAllOffer(){
        return new ResponseEntity<>(this.formationService.findAllCv(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Optional<Formation>> getCv(@PathVariable Long id){
        return ResponseEntity.ok(this.formationService.findOne(id));
    }
    @PutMapping("{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Formation> save(@PathVariable Long id,@RequestBody Formation formation){
        return ResponseEntity.ok(this.formationService.create(id, formation));
    }
}
