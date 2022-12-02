package com.example.libraryapp.repository;

import com.example.libraryapp.model.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rentals, Integer> {

    @Query("SELECT rentals FROM Rentals rentals WHERE rentals.userid = ?1")
    public List<Rentals> getRentalsByUserId(int userid);

    @Query("SELECT rentals FROM Rentals rentals WHERE rentals.bookid = ?1")
    public Rentals getRentalsByBookID(int bookid);
}
