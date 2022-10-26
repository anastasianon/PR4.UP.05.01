package com.example.pr2.repo;

import com.example.pr2.Models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepos extends CrudRepository<Student,Long> {
    Student findBysurnameContains(String surname);

}
