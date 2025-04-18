package com.nefisa.feelingstracker.controller;

import com.nefisa.feelingstracker.entity.Feeling;
import com.nefisa.feelingstracker.service.FeelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feelings")
public class FeelingController {

    private final FeelingService feelingService;

    @Autowired
    public FeelingController(FeelingService feelingService) {
        this.feelingService = feelingService;
    }

    @GetMapping
    public List<Feeling> getAllFeelings(){
        return feelingService.getFeelings();
    }

    @GetMapping("/{id}")
    public Feeling getFeelingById(@PathVariable Long id){
        return feelingService.getFeelingById(id).orElse(null);
    }


    @PostMapping
    public Feeling addNewFeeling(@RequestBody Feeling newFeeling){
        return feelingService.createFeeling(newFeeling);
    }

    @PutMapping("/{id}")
    public Feeling updateFeeling(@PathVariable Long id,
                                 @RequestBody Feeling updatedFeeling){
        return feelingService.updateFeeling(id,updatedFeeling);
    }

    @DeleteMapping("/{id}")
    public List<Feeling> deleteFeelingById(@PathVariable Long id){
        feelingService.deleteFeeling(id);
        return getAllFeelings();
    }
}
