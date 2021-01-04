package com.maliware.let.srecruit.service;

import com.maliware.let.srecruit.model.Cv;
import com.maliware.let.srecruit.model.Formation;
import com.maliware.let.srecruit.repository.CvRepository;
import com.maliware.let.srecruit.repository.FormationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationService {
    private final FormationRepository formationRepository;
    private final CvRepository cvRepository;

    public FormationService(FormationRepository formationRepository, CvRepository cvRepository) {
        this.formationRepository = formationRepository;
        this.cvRepository = cvRepository;
    }

    public Formation create(Long cvId, Formation formation){
        Cv cv = cvRepository.getOne(cvId);
        formation.setCv(cv);
        return formationRepository.saveAndFlush(formation);
    }

    public Formation upDate(Long id, Formation newCv){
        var oldCv = formationRepository.findById(id);
        if (oldCv.isPresent()){
            return formationRepository.saveAndFlush(newCv);
        } else {
            return oldCv.orElse(newCv);
        }
    }

    public Optional<Formation> findOne(Long id){
        return formationRepository.findById(id);
    }
    public List<Formation> findAllCv(){
        return formationRepository.findAll();
    }
}
