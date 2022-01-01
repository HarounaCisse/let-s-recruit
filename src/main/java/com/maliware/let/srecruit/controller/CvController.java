package com.maliware.let.srecruit.controller;

import com.maliware.let.srecruit.config.CustomAMQPProperties;
import com.maliware.let.srecruit.model.Cv;
import com.maliware.let.srecruit.service.CvService;
import com.maliware.let.srecruit.shared.CodeGenerator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/cv")
public class CvController {

    private final CvService cvService;
    private final CodeGenerator codeGenerator;
    private final RabbitTemplate template;
    @Autowired
    CustomAMQPProperties properties;

    public CvController(CvService cvService, CodeGenerator codeGenerator, RabbitTemplate template) {
        this.cvService = cvService;
        this.codeGenerator = codeGenerator;
        this.template = template;
    }

    @GetMapping
    ResponseEntity<List<Cv>> getAllOffer(){
        return new ResponseEntity<>(this.cvService.findAllCv(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Optional<Cv>> getCv(@PathVariable Long id){
        System.out.println("Getting Cv id :" +this.cvService.findOne(id));
        return ResponseEntity.ok(this.cvService.findOne(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Cv> save(@RequestBody Cv cv){
        return ResponseEntity.ok(this.cvService.create(cv));
    }

    @PutMapping("{id}")
    public ResponseEntity<Cv> update(@PathVariable Long id, @RequestBody Cv cv) {
        var response = cvService.upDate(id, cv);
        try {
            if (response != null){
                return ResponseEntity.ok(response);
            }
        }catch (NoSuchElementException e){
            System.out.println("Cv not Updated "+e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PutMapping("{id}/{cv}")
    @ResponseStatus(HttpStatus.OK)
    public void postuler(@PathVariable String id, @PathVariable String cv){
        try {
            Long offerId = Long.parseLong(id);
            Long cvId = Long.parseLong(cv);
         this.cvService.applyToJob(offerId, cvId);
        }catch (Exception e){
            System.out.println("Cv not created "+e.getMessage());
        }
    }

    @PostMapping("amq")
    public ResponseEntity<?> post(@RequestBody Cv cv){
//        messageChannel.send(MessageBuilder.withPayload(cv)
//                .build());
//        Message<Cv> message = MessageBuilder.withPayload(cv).build();
//        messageChannel.send(message);
        // send the payload to both systems like this
        //is going to thrown an Exception for DB part.
       // cv = this.cvService.create(cv);
        cv.setCode(codeGenerator.generateCode());
        template.convertAndSend(properties.getQueueName(), cv);
       return new ResponseEntity<>(cv, HttpStatus.CREATED);

    }

}
