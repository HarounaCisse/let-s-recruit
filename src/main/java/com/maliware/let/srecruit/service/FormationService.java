package com.maliware.let.srecruit.service;

import com.maliware.let.srecruit.model.Cv;
import com.maliware.let.srecruit.model.Formation;
import com.maliware.let.srecruit.repository.CvRepository;
import com.maliware.let.srecruit.repository.FormationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormationService {
    private final FormationRepository formationRepository;
    private final CvRepository cvRepository;

    public FormationService(FormationRepository formationRepository, CvRepository cvRepository) {
        this.formationRepository = formationRepository;
         this.cvRepository = cvRepository;
    }

    public List<Formation> create(Long cvId, Iterable<Formation> formation){
        Cv cv = cvRepository.getOne(cvId);
        //We have to fix this one
        formation.forEach(formation1 -> formation1.setCv(cv));
//        formation.spliterator().forEachRemaining(data -> data.setCv(cv));
//        if (formation.iterator().hasNext()){
//            formation = formation;
//        }
        // formation.setCv(cv);
        //formation.setCv(cv);
        return formationRepository.saveAll(formation);
    }

    public Formation upDate(Long id, Formation newFormmation){
        var oldCv = formationRepository.findById(id);
        if (oldCv.isPresent()){
            return formationRepository.saveAndFlush(newFormmation);
        } else {
            return oldCv.orElse(newFormmation);
        }
    }

    public Optional<Formation> findOne(Long id){
        return formationRepository.findById(id);
    }
    public List<Formation> findAllFormation(){
        return formationRepository.findAll();
    }

    public List<Formation> getUserFormations(Long cvId){
        //System.out.println(formationRepository.findByCvId(cvId));
        if (findOne(cvId).isPresent()){
            return formationRepository.findByCvId(cvId);
        }
        return Collections.emptyList();
    }
}
