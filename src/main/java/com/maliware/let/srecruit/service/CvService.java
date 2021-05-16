package com.maliware.let.srecruit.service;


import com.maliware.let.srecruit.model.Cv;

import com.maliware.let.srecruit.model.Offer;
import com.maliware.let.srecruit.repository.CvRepository;
import com.maliware.let.srecruit.repository.OfferRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CvService {
    private final CvRepository cvRepository;
    private final OfferRepository offerRepository;


    public CvService(CvRepository cvRepository, OfferRepository offerRepository) {
        this.cvRepository = cvRepository;
        this.offerRepository = offerRepository;
    }

    public Cv create(Cv cv){
        return cvRepository.saveAndFlush(cv);
    }

    public Cv upDate(Long id, Cv newCv){
        Objects.requireNonNull(newCv,"Cv can not be null");
//if (id == null) {
//throw new IllegalArgumentException("ID cannot be null");
//}
        var oldCv = cvRepository.findById(id);
        if (oldCv.isPresent()){
            return cvRepository.save(newCv);
        } else {
            return oldCv.orElse(newCv);
        }
    }

    public Optional<Cv> findOne(Long id){
       return cvRepository.findById(id);
    }
    public List<Cv> findAllCv(){
        return cvRepository.findAll();
    }

    public void deleteCv(Long id){
        cvRepository.deleteById(id);
    }

    public Optional<Cv> applyToJob(Long id, Long currentCv) {
        //The || is not a test for “either-or”. If both
        //conditions are true, the result is true.
        //NB: DON't Do LIKE THIS && which only checks
        //if the the first one is true e.g:
        //0 < 100 && 100 < 100  return false
        if (findOne(id).isPresent() || findOne(currentCv).isPresent()){
            Offer offer = offerRepository.findById(id).get();
            Cv cv = cvRepository.findById(currentCv).get();
            offer.addCv(cv);
            return Optional.of(upDate(currentCv, cv));
        }
            return Optional.empty();
    }



}
