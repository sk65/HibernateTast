package com.testHibernate;

import com.testHibernate.dao.JPAImple.AuthorJpaImpl;
import com.testHibernate.dao.hibernateImpl.AuthorHibernateImpl;
import com.testHibernate.dao.interfeses.AuthorDao;
import com.testHibernate.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Start {
    public static void main(String[] args) {
     /*   Session session = HibernateUtil.getSessionFactory().openSession();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        Author author = authorDaoImpl.getAuthorById(2);
        System.out.println(author.toString());

        List<Author> authors = authorDaoImpl.getAuthorList();
        for (Author author1 : authors) {
            System.out.println(author1.toString());
        }*/

//        AuthorDao dao = new AuthorHibernateImpl();
//        List<Author> authors = dao.findAll();
//        for (Author author1 : authors) {
//            System.out.println(author1.toString());
//        }
//
//        Author author = dao.findById(1L);
//        System.out.println(author);
//        dao.save(new Author());
        //jpaExample();

        AuthorDao dao = new AuthorJpaImpl();
        Author author = new Author("Жуков");
        author.setId(19);
        dao.delete(author);
        List<Author> authors = dao.findAll();
        authors.forEach(System.out::println);

    }

    static void jpaExample() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("JPA");
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Author("Дементий"));
        entityManager.getTransaction().commit();
        List<Author> authors = entityManager.createQuery("FROM Author ").getResultList();
        authors.forEach(System.out::println);
        entityManager.close();
    }
}
