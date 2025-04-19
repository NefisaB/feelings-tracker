package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.dto.FeelingRequestDTO;
import com.nefisa.feelingstracker.entity.Feeling;
import com.nefisa.feelingstracker.repositories.FeelingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Feeling createFeeling(FeelingRequestDTO dto){
        return feelingRepository.save(mapToEntity(dto));
    }

    public Feeling updateFeeling(Long id, FeelingRequestDTO dto){
        return feelingRepository.findById(id).map(existingFeeling ->{
            existingFeeling.setDescription(dto.getDescription());
            return feelingRepository.save(existingFeeling);
        }).orElse(null);
    }

    public void deleteFeeling(Long id){
        if(feelingRepository.existsById(id)){
            feelingRepository.deleteById(id);
        }
    }

    private Feeling mapToEntity(FeelingRequestDTO dto){
        return new Feeling(dto.getDescription(), LocalDateTime.now());
    }


}
