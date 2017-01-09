package com.codeup;

import com.codeup.model.Post;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class HibernatePostsDao implements Posts {
    private Session session;

    public HibernatePostsDao(Session session) {
        this.session = session;
    }

    private List<Post> posts;

    public HibernatePostsDao() {
        posts = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<Post> all() {
        return session.createQuery("from Post").list();
    }

    @Override
    public void insert(Post post) {
        Transaction tx = session.beginTransaction();
        session.save(post);
        tx.commit();
    }

    @Override
    public Post save(Post post) {
        Transaction tx = session.beginTransaction();
        session.save(post);
        tx.commit();
        return post;
    }

    @Override
    public Post findById(int postId) {
        Query query = session.createQuery("from Post where id = :id ");
        query.setParameter("id", postId);
        return (Post) query.getSingleResult();
    }
    @Override
    public Post update(Post post) {
        Transaction tx = session.beginTransaction();
        session.update(post);
        tx.commit();
        return post;
    }

    @Override
    public void delete(int id) {
        Post post = DaoFactory.getPostsDao().findById(id);
        Transaction tx = session.beginTransaction();
        session.delete(post);
        tx.commit();
    }
}