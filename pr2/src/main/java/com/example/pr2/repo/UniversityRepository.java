package com.example.pr2.repo;

import com.example.pr2.Models.University;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UniversityRepository extends CrudRepository<University,Long> {
    University findByTitleuniversity(String titleuniversity);
}

