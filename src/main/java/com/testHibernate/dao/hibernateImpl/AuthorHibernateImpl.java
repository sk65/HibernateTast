package com.testHibernate.dao.hibernateImpl;

import com.testHibernate.dao.HibernateUtil;
import com.testHibernate.dao.interfeses.AuthorDao;
import com.testHibernate.entity.Author;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Query;
import java.util.List;

public class AuthorHibernateImpl implements AuthorDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Author> findAll() {
        try (Session session = sessionFactory.openSession()) {

        /*//Native
        String sql = "SELECT * FROM author";
        Query query = session.createSQLQuery(sql).addEntity(Author.class);
        List<Author> authors = query.getResultList();
        return authors;*/

            // HQL
            // Author author = session.find(Author.class,);
            //System.out.println("from find oll  " + author.toString());
            return session.createQuery("from Author", Author.class).list();

       /* //Criteria API
        Criteria criteria = session.createCriteria(Author.class);
        List<Author> authors = criteria.list(); session.close();
        return authors;*/
        }
    }

    @Override
    public Author findById(Long id) {

      /*
        try (Session session = sessionFactory.openSession();) {
            return session.get(Author.class, id);
        }*/

        //Native
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM author where id = ?;";
        Query query = session.createSQLQuery(sql).addEntity(Author.class);
        query.setParameter(1, id);
        Author author = (Author) query.getResultList().get(0);
        session.close();
        return author;
       /* //HQl
        try (Session session = sessionFactory.openSession()) {
            String sql = "from Author where id = :id";
            Query query = session.createQuery(sql, Author.class);
            query.setParameter("id", id);
            return (Author) query.getSingleResult();
        }*/
        //Criteria API
//        try (Session session = sessionFactory.openSession()) {
//            Criteria criteria = session.createCriteria(Author.class);
//            criteria.add(Restrictions.eq("id", id));
//            Author author = (Author) criteria.uniqueResult();
//            return author;
//        }

    }

    @Override
    public void save(Author author) {
//        try (Session session = sessionFactory.openSession()) {
//            session.getTransaction().begin();
//            session.save(author);
//            session.getTransaction().commit();
//        }
        // Native
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            String sql = "Insert into author values(null,? );";
            Query query = session.createSQLQuery(sql);
            query.setParameter(1, author.getName());
            query.executeUpdate();
            session.getTransaction().commit();
        }

        //HQL
        //  HQL does not support the insert operation

        //Criteria
        // --------------------

    }

    @Override
    public void update(Author author) {
//        try (Session session = sessionFactory.openSession()) {
//            session.getTransaction().begin();
//            session.update(author);
//            session.getTransaction().commit();
//        }
        //Native
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            String sql = "update author set name = ? where id = ? );";
            Query query = session.createSQLQuery(sql);
            query.setParameter(1, author.getName());
            query.setParameter(2, author.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        }
        //JPQL
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            String sql = "UPDATE Author SET name = :name where id=:id";
            Query query = session.createQuery(sql, Author.class);
            query.setParameter("name", author.getName());
            query.setParameter("id", author.getId());
            query.executeUpdate();
        }

    }

    @Override
    public void delete(Author author) {
//        try (Session session = sessionFactory.openSession()) {
//            session.getTransaction().begin();
//            session.delete(author);
//            session.getTransaction().commit();
//        }
//        try (Session session = sessionFactory.openSession()) {
//            session.getTransaction().begin();
//            String sql = "DELETE FROM author WHERE (id = ?);";
//            Query query = session.createSQLQuery(sql);
//            query.setParameter(1, author.getId());
//            query.executeUpdate();
//            session.getTransaction().commit();
//        }

    }
}
