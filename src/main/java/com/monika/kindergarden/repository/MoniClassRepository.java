package com.monika.kindergarden.repository;

import com.monika.kindergarden.model.MoniClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoniClassRepository extends JpaRepository<MoniClass, Integer> {
}
