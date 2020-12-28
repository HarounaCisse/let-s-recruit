package com.maliware.let.srecruit.controller;

import com.maliware.let.srecruit.model.Cv;
import com.maliware.let.srecruit.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cv")
public class CvController {
    @Autowired
    private CvService cvService;

    @GetMapping
    ResponseEntity<List<Cv>> getAllOffer(){
        return new ResponseEntity<>(this.cvService.findAllCv(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Optional<Cv>> getCv(@PathVariable Long id){
        return ResponseEntity.ok(this.cvService.findOne(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Cv> save(@RequestBody Cv cv){
        return ResponseEntity.ok(this.cvService.create(cv));
    }

    @PutMapping("{id}/{cv}")
    @ResponseStatus(HttpStatus.OK)
    public void applyFor(@PathVariable Long id, @PathVariable Long cv){
         this.cvService.applyToJob(id, cv);
    }
//    ResponseEntity<Cv> applyFor(@PathVariable Long id, @PathVariable Long cv){
//        return ResponseEntity.ok(this.cvService.applyToJob(id, cv));
//    }
}
