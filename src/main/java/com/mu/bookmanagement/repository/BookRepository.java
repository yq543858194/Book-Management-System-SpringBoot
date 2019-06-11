package com.mu.bookmanagement.repository;

import com.mu.bookmanagement.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    public BookEntity findByIsbn (String isbn);

    public List<BookEntity> findAllByIsbn (String ISBN);

    public List<BookEntity> findAllByAuthor (String author);

    public List<BookEntity> findAllByDescription (String description);

    public List<BookEntity> findAllByName (String name);
}
