package com.maliware.let.srecruit.controller;

import com.maliware.let.srecruit.model.Competence;
import com.maliware.let.srecruit.service.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/competence")
public class CompetenceController {
    @Autowired
    private CompetenceService competenceService;

    @PostMapping()
    ResponseEntity<Optional<Competence>> save(@RequestBody Competence competence){
        return ResponseEntity.ok(this.competenceService.create(competence));
    }

    @PutMapping("{id}")
        //@ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Competence> save(@PathVariable String id, @RequestBody Competence competences){
        Long cvId = Long.parseLong(id);
        return ResponseEntity.ok(this.competenceService.create(cvId, competences));
    }

    @GetMapping()
    ResponseEntity<List<Competence>> getList(){
        return ResponseEntity.ok(this.competenceService.competenceList());
    }
    @GetMapping("{id}")
    ResponseEntity<List<Competence>> userCompetences(@PathVariable String id){
        Long cvId = Long.parseLong(id);
        try {
            return ResponseEntity.ok(this.competenceService.userCreatedCompetences(cvId));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
