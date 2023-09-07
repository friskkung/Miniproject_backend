package com.Watcharakorn.timeTableSheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Watcharakorn.timeTableSheduler.model.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Integer> {

}
