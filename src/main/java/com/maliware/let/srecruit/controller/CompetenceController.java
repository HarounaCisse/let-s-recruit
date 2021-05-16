package com.maliware.let.srecruit.controller;


import com.maliware.let.srecruit.exception.CustomException;
import com.maliware.let.srecruit.model.Competence;
import com.maliware.let.srecruit.service.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/competence")
public class CompetenceController {
     private RabbitTemplateConfigurer rabbitTemplateConfigurer;
    @Autowired
    private CompetenceService competenceService;

    @PostMapping()
    ResponseEntity<Optional<Competence>> save(@RequestBody Competence competence){
        return ResponseEntity.ok(this.competenceService.create(competence));
    }

    @PutMapping("{id}")
    ResponseEntity<List<Competence>> save(@PathVariable String id, @RequestBody List<Competence> competences) {
        Long cvId = Long.parseLong(id);
        //System.out.println("Controller method " );
        if (competences.isEmpty()){
            throw new CustomException("List Shouldn't be Empty");
        }
        return ResponseEntity.ok(this.competenceService.create(cvId, competences));
    }

    @GetMapping()
    ResponseEntity<List<Competence>> getList(){
        return ResponseEntity.ok(this.competenceService.competenceList());
    }
    @GetMapping("{id}")
    ResponseEntity<List<Competence>> userCompetences(@PathVariable String id){
//        boolean nonNull = Objects.nonNull(id);
//        if (!nonNull ) {
//            throw new IllegalArgumentException("ID cannot be null");
//        }

        try {
            Long cvId = Long.parseLong(id);
            return ResponseEntity.ok(this.competenceService.userCreatedCompetences(cvId));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
