package com.testHibernate.dao.JPAImple;

import com.testHibernate.dao.JpaUtil;
import com.testHibernate.dao.interfeses.AuthorDao;
import com.testHibernate.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorJpaImpl implements AuthorDao {
    EntityManagerFactory managerFactory = JpaUtil.getManagerFactory();

    @Override
    public List<Author> findAll() {
        List<Author> authors;
        EntityManager entityManager = managerFactory.createEntityManager();
        //JPQL
        //authors = entityManager.createQuery("FROM Author", Author.class).getResultList();

        //Native
        //authors =  entityManager.createNativeQuery("SELECT * from author;",Author.class).getResultList();

        //Criteria API
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Author> authorCriteria = cb.createQuery(Author.class);

        Root<Author> authorRoot = authorCriteria.from(Author.class);

        authorCriteria.select(authorRoot);

        authors = entityManager.createQuery(authorCriteria).getResultList();
        entityManager.close();
        return authors;

    }

    @Override
    public Author findById(Long id) {
        EntityManager entityManager = managerFactory.createEntityManager();
        Author author;
        //JPQL
        // author = entityManager.find(Author.class, id);
        // author = entityManager.createQuery("FROM  Author ", Author.class).getSingleResult();

        //Native
        Query query = entityManager.createNativeQuery("SELECT * from author where  id = ?;", Author.class);
        query.setParameter(1, id);
        author = (Author) query.getSingleResult();

        //Criteria
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Author> authorCriteria = cb.createQuery(Author.class);
//        Root<Author> authorRoot = authorCriteria.from(Author.class);
//        authorCriteria.select(authorRoot);
//        authorCriteria.where(cb.equal(authorRoot.get("id"), id));
//        author = entityManager.createQuery(authorCriteria).getSingleResult();
//        entityManager.close();
        return author;
    }

    @Override
    public void save(Author author) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //
        //entityManager.persist(author);

        //Native
//        Query query = entityManager.createNativeQuery("INSERT INTO author" +
//                " VALUES (null,?)", Author.class);
//        query.setParameter(1, author.getName());
//        query.executeUpdate();

        //HQL
        // --------------------

        //Criteria
        // -------
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(Author author) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //  entityManager.persist(author);

        // Native
//        Query query = entityManager.createNativeQuery("UPDATE  author set name =? where id =?", Author.class);
//        query.setParameter(1, author.getName());
//        query.setParameter(2, author.getId());
//        query.executeUpdate();

        //JPQL
        String sql = "UPDATE Author SET name = :name where id=:id";
        Query query = entityManager.createQuery(sql, Author.class);
        query.setParameter("name", author.getName());
        query.setParameter("id", author.getId());
        query.executeUpdate();

        entityManager.close();
        entityManager.getTransaction().commit();

    }

    @Override
    public void delete(Author author) {
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //JPQL
//        String sql = "DELETE FROM  Author where id = :id";
//        Query query = entityManager.createQuery(sql);
//        query.setParameter("id", author.getId());
//        query.executeUpdate();

        //Native
//        Query query = entityManager.createNativeQuery("DELETE  FROM author where id =?");
//        query.setParameter(1, author.getId());
//        query.executeUpdate();

        //Criteria
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        // create delete
//        CriteriaDelete<Author> delete = cb.createCriteriaDelete(Author.class);
//
//        // set the root class
//        Root<Author> e = delete.from(Author.class);
//
//        // set where clause
//        delete.where(cb.lessThanOrEqualTo(e.get("id"), author.getId()));
//
//        // perform update
//        entityManager.createQuery(delete).executeUpdate();
        Author author1 = entityManager.find(Author.class, author.getId());
        entityManager.remove(author1);


        entityManager.close();
        entityManager.getTransaction().commit();
    }
}
