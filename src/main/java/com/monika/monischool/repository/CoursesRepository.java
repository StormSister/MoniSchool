package com.monika.monischool.repository;

import com.monika.monischool.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path="courses")
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    List<Courses> findByOrderByNameDesc();

    List<Courses> findByOrderByName();
}
