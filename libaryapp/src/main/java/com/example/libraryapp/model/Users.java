package com.example.libraryapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(name="UserId")
    private int userID;

    @Column(name="FName")
    private String firstName;

    @Column(name="LName")
    private String lastName;

    @Column(name="StreetAddress")
    private String streetAddress;

    @Column(name="City")
    private String city;

    @Column(name="State")
    private String state;

    @Column(name="ZipCode")
    private int zipcode;

    @Column(name="Phone")
    private String phone;

    @Column(name="MemberShipDate")
    private Date membershipDate;

    @Column(name="isLibrarian")
    private boolean isLibrarian;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(Date membershipDate) {
        this.membershipDate = membershipDate;
    }

    public boolean getIsLibrarian() {
        return isLibrarian;
    }

    public void setIsLibrarian(boolean isLibrarian) {
        this.isLibrarian = isLibrarian;
    }
}
