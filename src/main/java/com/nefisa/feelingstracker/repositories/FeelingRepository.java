package com.nefisa.feelingstracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nefisa.feelingstracker.entity.Feeling;

public interface FeelingRepository extends JpaRepository<Feeling, Long> {
}
