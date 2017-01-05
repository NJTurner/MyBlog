package com.codeup;


import java.util.List;

public interface Posts {

    List<Post> all();
    Long insert(Post post);
    void save(Post post);
}
