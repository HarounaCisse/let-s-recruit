package com.maliware.let.srecruit.repository;

import com.maliware.let.srecruit.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface OfferRepository extends JpaRepository<Offer, Long>{
    Set<Offer> findByCvsId(Long id);
}
