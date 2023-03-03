package com.webproject.kurs.repo;

import com.webproject.kurs.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}

