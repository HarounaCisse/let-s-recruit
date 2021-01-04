package com.maliware.let.srecruit.service;


import com.maliware.let.srecruit.model.Cv;
import com.maliware.let.srecruit.model.Formation;
import com.maliware.let.srecruit.model.Offer;
import com.maliware.let.srecruit.repository.CvRepository;
import com.maliware.let.srecruit.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CvService {
    private final CvRepository cvRepository;
    private final OfferRepository offerRepository;

    public CvService(CvRepository cvRepository, OfferRepository offerRepository) {
        this.cvRepository = cvRepository;
        this.offerRepository = offerRepository;
    }

    public Cv create(Cv cv){

        ///cv.getFormations().forEach(cv::addFormation);
       // cv.getFormations().iterator().forEachRemaining(formation -> cv.addFormation(formation));
//        Formation formation = cv.getFormations().iterator().next();
//        cv.addFormation(formation);
        return cvRepository.saveAndFlush(cv);
    }

    public Cv upDate(Long id, Cv newCv){
        var oldCv = cvRepository.findById(id);
        if (oldCv.isPresent()){
            return cvRepository.saveAndFlush(newCv);
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
            Offer offer = offerRepository.findById(id).get();
            Cv cv = cvRepository.findById(currentCv).get();
            offer.addCv(cv);
            return Optional.ofNullable(upDate(currentCv, cv));
    }


}
