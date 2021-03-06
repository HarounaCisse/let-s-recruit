package com.maliware.let.srecruit.controller;

import com.maliware.let.srecruit.model.Cv;
import com.maliware.let.srecruit.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.NoSuchElementException;
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
        System.out.println("Getting Cv id :" +this.cvService.findOne(id));
        return ResponseEntity.ok(this.cvService.findOne(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Cv> save(@RequestBody Cv cv){
        return ResponseEntity.ok(this.cvService.create(cv));
    }

    @PutMapping("{id}")
    public ResponseEntity<Cv> update(@PathVariable Long id, @RequestBody Cv cv) {
        var response = cvService.upDate(id, cv);
        try {
            if (response != null){
                return ResponseEntity.ok(response);
            }
        }catch (NoSuchElementException e){
            System.out.println("Cv not Updated "+e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PutMapping("{id}/{cv}")
    @ResponseStatus(HttpStatus.OK)
    public void postuler(@PathVariable String id, @PathVariable String cv){
        try {
            Long offerId = Long.parseLong(id);
            Long cvId = Long.parseLong(cv);
         this.cvService.applyToJob(offerId, cvId);
        }catch (Exception e){
            System.out.println("Cv not created "+e.getMessage());
        }
    }

}
