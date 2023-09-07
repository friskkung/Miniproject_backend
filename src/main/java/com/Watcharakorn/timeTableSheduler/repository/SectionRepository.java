package com.Watcharakorn.timeTableSheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Watcharakorn.timeTableSheduler.model.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

}
