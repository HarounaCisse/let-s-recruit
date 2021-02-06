package com.maliware.let.srecruit.service;


import com.maliware.let.srecruit.model.Competence;
import com.maliware.let.srecruit.model.Cv;
import com.maliware.let.srecruit.repository.CompetenceRepository;
import com.maliware.let.srecruit.repository.CvRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompetenceService {
    private final CompetenceRepository competenceRepository;
    private final CvRepository cvRepository;
    private static final int EMPTY = 0;

    public CompetenceService(CompetenceRepository competenceRepository, CvRepository cvRepository) {
        this.competenceRepository = competenceRepository;
        this.cvRepository = cvRepository;
    }

    public void injectCommonSkills(){
        Set<String> list = Set.of("Business Management","IT Manageur",
                "Developpeur FrontEnd","Big Data Analysts","Docteur Generaliste",
                "Developpeur BackEnd");
        if (this.competenceRepository.count() == EMPTY ){
            list.forEach(competence -> this.competenceRepository.save(new Competence(competence)));
        }
    }

    public Optional<Competence> create(Competence competence){

        return  Optional.of(this.competenceRepository.save(competence));
    }
    public Competence create(Long cvId, Competence competences) {
        Cv cv = this.cvRepository.getOne(cvId);
        //competences.forEach(data -> data.setCv(cv));
        competences.setCv(cv);
        return this.competenceRepository.save(competences);
    }

    public List<Competence> competenceList(){
        return this.competenceRepository.findAll();
    }

    public List<Competence> userCreatedCompetences(Long cvId) throws IllegalArgumentException {
        Objects.requireNonNull(cvId,"CV can't be NULL");
        if (cvId == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return this.competenceRepository.findByCvId(cvId);
    }

}
