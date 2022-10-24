package com.example.pr2.repo;

import com.example.pr2.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepos extends CrudRepository<Post,Long> {
    List<Post> findByTitleContains(String title);

}
