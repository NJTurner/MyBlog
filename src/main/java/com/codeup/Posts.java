package com.codeup;

import com.codeup.model.Post;

import java.util.List;

public interface Posts {

    List<Post> all();
    void insert(Post post);
    Post save(Post post);
    Post findById(int postId);
    Post update(Post post);
    public void delete(int id);
}
