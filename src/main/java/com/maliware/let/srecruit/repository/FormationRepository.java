package com.maliware.let.srecruit.repository;

import com.maliware.let.srecruit.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {

}
