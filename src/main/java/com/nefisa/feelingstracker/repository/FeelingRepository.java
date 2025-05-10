package com.nefisa.feelingstracker.repository;

import com.nefisa.feelingstracker.entity.Feeling;
import com.nefisa.feelingstracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeelingRepository extends JpaRepository<Feeling, Long> {

    List<Feeling> findByOwner(User owner);
}
