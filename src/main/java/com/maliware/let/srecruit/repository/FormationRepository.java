package com.maliware.let.srecruit.repository;

import com.maliware.let.srecruit.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByCvId(Long id);
//    @Query("Select c from formation c where c.cv.id =?1")
//    List<Formation> getUserCvs(Long cvId);
}
