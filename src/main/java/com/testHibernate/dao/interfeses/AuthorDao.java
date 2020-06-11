package com.testHibernate.dao.interfeses;

import com.testHibernate.entity.Author;

import java.util.List;

public interface AuthorDao {
    //read
    List<Author> findAll();

    Author findById(Long id);

    //create
    void save(Author author);

    //update
    void update(Author author);

    //delete
    void delete(Author author);
}
