package com.example.libraryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.libraryapp.model.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, java.lang.Integer> {

    @Query("SELECT books FROM Books books WHERE books.title = ?1")
    public Books getBookByTitle(String title);
}
