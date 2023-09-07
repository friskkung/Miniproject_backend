package com.Watcharakorn.timeTableSheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Watcharakorn.timeTableSheduler.model.Classes;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Integer> {

}
