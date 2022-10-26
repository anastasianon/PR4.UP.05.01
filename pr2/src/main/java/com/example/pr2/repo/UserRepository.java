package com.example.pr2.repo;

import com.example.pr2.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByLogin(String username);

}

