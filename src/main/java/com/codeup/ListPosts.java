package com.codeup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListPosts implements Posts {
    private List<Post> posts;
    private Connection connection = null;

    public ListPosts() {
        posts = new ArrayList<>();
    }

    @Override
    public List<Post> all() {
        return posts;
    }

    @Override
    public void save(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
    }

    @Override
    public Long insert(Post post) {
    if(posts == null){
        posts= generatePosts();
    }
    post.setId((long) posts.size()+1);
    posts.add(post);
    return post.getId();
    }

    private ArrayList<Post> generatePosts(){return null;}
}
