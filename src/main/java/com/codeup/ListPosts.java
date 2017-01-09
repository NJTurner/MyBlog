//package com.codeup;
//
//import com.codeup.model.Post;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ListPosts implements Posts {
//
//    private Session session;
//
//    public ListPosts(Session session) {
//        this.session = session;
//    }
//
//    private List<Post> posts;
//    private Connection connection = null;
//
//    public ListPosts() {
//        posts = new ArrayList<>();
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<Post> all() {
//        return session.createQuery("from Post").list();
//    }
//
//    @Override
//    public void insert(Post post) {
//        Transaction tx = session.beginTransaction();
//        session.save(post);
//        tx.commit();
//    }
//
//    @Override
//    public void save(Post post) {
//        Transaction tx = session.beginTransaction();
//        session.save(post);
//        tx.commit();
//        return post;
//
//    }
//
//    public Post find(long id){
//        return (Post) session.createQuery(("from Post where id = :id"))
//    }
////    @Override
////    public void save(Post post) {
////        post.setId(posts.size() + 1);
////        posts.add(post);
////    }
////
////    @Override
////    public Long insert(Post post) {
////    if(posts == null){
////        posts= generatePosts();
////    }
////    post.setId((long) posts.size()+1);
////    posts.add(post);
////    return post.getId();
////    }
////
////    private ArrayList<Post> generatePosts(){return null;}
//}
