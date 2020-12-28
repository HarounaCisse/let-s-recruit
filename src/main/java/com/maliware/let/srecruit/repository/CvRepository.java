package com.maliware.let.srecruit.repository;

import com.maliware.let.srecruit.model.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvRepository extends JpaRepository<Cv,Long> {
    //GET LIST OF OFFERS OF A CV
}
