package com.webproject.kurs.repo;

import com.webproject.kurs.models.Post;
import com.webproject.kurs.models.PostSick;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}

