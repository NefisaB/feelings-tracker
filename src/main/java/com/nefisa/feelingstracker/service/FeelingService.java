package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.entity.Feeling;
import com.nefisa.feelingstracker.repositories.FeelingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeelingService {

    private final FeelingRepository feelingRepository;

    @Autowired

    public FeelingService(FeelingRepository feelingRepository) {
        this.feelingRepository = feelingRepository;
    }

    public List<Feeling> getFeelings(){
        return feelingRepository.findAll();
    }

    public Optional<Feeling> getFeelingById(Long id){
        return feelingRepository.findById(id);
    }

    public Feeling createFeeling(Feeling feeling){
        return feelingRepository.save(feeling);
    }

    public Feeling updateFeeling(Long id, Feeling feeling){
        if(feelingRepository.existsById(id)){
            feeling.setId(id);
            return feelingRepository.save(feeling);
        }
        return null;
    }

    public boolean deleteFeeling(Long id){
        if(feelingRepository.existsById(id)){
            feelingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
