package com.codeup;

import com.codeup.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Posts extends CrudRepository<Post, Long>{
    public Page<Post> findAll(Pageable pageable);
}
