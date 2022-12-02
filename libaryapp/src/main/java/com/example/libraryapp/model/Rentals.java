package com.example.libraryapp.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Rentals")
public class Rentals {
    @Id
    @Column(name="RentalId")
    private int rentalID;

    @Column(name="BookId")
    private int bookid;

    @Column(name="UserId")
    private int userid;

    @Column(name="CheckoutDate")
    private Date checkoutDate;

    @Column(name="ScheduledReturnDate")
    private Date scheduledReturnDate;

    public int getRentalID() {
        return rentalID;
    }

    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getScheduledReturnDate() {
        return scheduledReturnDate;
    }

    public void setScheduledReturnDate(Date scheduledReturnDate) {
        this.scheduledReturnDate = scheduledReturnDate;
    }
}
