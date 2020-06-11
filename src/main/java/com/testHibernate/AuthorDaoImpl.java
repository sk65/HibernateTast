package com.testHibernate;

import com.testHibernate.dao.HibernateUtil;
import com.testHibernate.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorDaoImpl {
    private final SessionFactory sessionFactory;

    public AuthorDaoImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery cq = criteriaBuilder.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        List<Author> authors = query.getResultList();
        session.close();

        return authors;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        session.close();
        return author;
    }
}
