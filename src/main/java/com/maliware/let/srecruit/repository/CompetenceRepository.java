package com.maliware.let.srecruit.repository;

import com.maliware.let.srecruit.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    List<Competence> findByCvId(Long id);
}
