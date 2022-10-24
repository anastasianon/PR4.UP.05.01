package com.example.pr2.repo;

import com.example.pr2.Models.Prepod;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrepodRepos extends CrudRepository<Prepod,Long>{
    List<Prepod> findBysurname(String surname);

}
