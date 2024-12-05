package com.monika.monischool.repository;

import com.monika.monischool.model.MoniClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoniClassRepository extends JpaRepository<MoniClass, Integer> {
}
