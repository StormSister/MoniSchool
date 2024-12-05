package com.monika.monischool.repository;

import com.monika.monischool.model.Holiday;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;




@Repository
public interface HolidaysRepository extends CrudRepository<Holiday, String> {


}

