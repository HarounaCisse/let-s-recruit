package com.maliware.let.srecruit.controller;

import com.maliware.let.srecruit.model.Cv;
import com.maliware.let.srecruit.model.Offer;
import com.maliware.let.srecruit.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("offer")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @GetMapping
    ResponseEntity<List<Offer>> getAllOffer(){
        return new ResponseEntity<>(this.offerService.getOfffers(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    ResponseEntity<Optional<Offer>> getOffer(@PathVariable Long id){
        return ResponseEntity.ok(this.offerService.findOne(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Offer> saveOffer(@RequestBody Offer offer){
        return ResponseEntity.ok(this.offerService.create(offer));
    }

}
