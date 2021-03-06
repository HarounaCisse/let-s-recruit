package com.maliware.let.srecruit.service;

import com.maliware.let.srecruit.model.Cv;
import com.maliware.let.srecruit.model.Offer;
import com.maliware.let.srecruit.repository.OfferRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class OfferService {
    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer create(Offer offer){
        return this.offerRepository.saveAndFlush(offer);
    }

    public List<Offer> getOfffers(){
        return  this.offerRepository.findAll();
    }

    public Optional<Offer> findOne(Long id){
        return offerRepository.findById(id);
    }

    public Set<Offer> getUserCVs(Long id){
        return this.offerRepository.findByCvsId(id);
    }

}
