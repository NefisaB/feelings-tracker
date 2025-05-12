package com.nefisa.feelingstracker.repository;

import com.nefisa.feelingstracker.entity.Feeling;
import com.nefisa.feelingstracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeelingRepository extends JpaRepository<Feeling, Long> {

    List<Feeling> findByOwner(User owner);
    Optional<Feeling> findByIdAndOwner(Long id, User owner);
}
