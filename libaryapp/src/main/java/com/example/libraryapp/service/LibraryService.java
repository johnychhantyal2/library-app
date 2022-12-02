package com.example.libraryapp.service;

import com.example.libraryapp.model.*;
import com.example.libraryapp.repository.BookRepository;
import com.example.libraryapp.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.libraryapp.repository.UserRepository;
import com.example.libraryapp.repository.CredentialRepository;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RentalRepository rentalRepository;

    // Create user
    public Users createUser(Users user1) {
        return userRepository.save(user1);
    }

    // Get the list of users
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    // Get the list of users
    public List<Books> getBooks() {
        return bookRepository.findAll();
    }

    // Get a user by id
    public Users getUserById(int id) {
        return userRepository.findById(id).get();
    }

    // Delete user
    public void deleteUserById(int userId) {
        if (userRepository.findById(userId).isPresent()) {
            credentialRepository.deleteById(userId);
            userRepository.deleteById(userId);
        } else {
            System.out.print("User with id: " + userId + " doesn't exist");
        }
    }

    // Update the user details
    public Users updateUser(int userId, Users userDetails) {
        Users user = userRepository.findById(userId).get();
        user.setPhone(userDetails.getPhone());
        return userRepository.save(user);
    }

    // Login user to the library app
    public Users loginUser(Login loginDetails) throws NullPointerException {
        Users user = new Users();
        String username = loginDetails.getUsername();
        System.out.print("username is :" + username);
        try {
            Credentials creds = credentialRepository.getCredsbyUserNameAndPassword(loginDetails.getUsername(), loginDetails.getPassword());
            if (creds == null) {
                System.out.print("Did not find credentials of the user!");
            } else {
                user = userRepository.findById(creds.getUserId()).get();
                if (user.getUserID() <= 0) {
                    System.out.print("No user record with given ID");
                }
            }
        } catch (NullPointerException e) {
            System.out.print("NullPointerException Caught");
        }
        return user;
    }

    // Register or sign up user to the library app
    public Users registerUser(Register userDetails) throws Exception {
        Users user = new Users();
        Credentials creds = new Credentials();

        // Save user info
        List<Users> users = getUsers();
        user.setUserID(users.size()+1);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setStreetAddress(userDetails.getStreetAddress());
        user.setCity(userDetails.getCity());
        user.setState(userDetails.getState());
        user.setZipcode(userDetails.getZipcode());
        user.setPhone(userDetails.getPhone());
        user.setMembershipDate(userDetails.getMembershipDate());
        user.setIsLibrarian(userDetails.getIsLibrarian());
        userRepository.save(user);

        // Save credential of above user
        creds.setUserId(user.getUserID());
        creds.setUsername(userDetails.getUsername());
        creds.setPassword(userDetails.getPassword());
        credentialRepository.save(creds);

        return user;
    }

    // Get a book by its id
    public Books getBookById(int id) {
        return bookRepository.findById(id).get();
    }

    // Get a book by title
    public Books getBookByTitle(String title) {
        return bookRepository.getBookByTitle(title);
    }

    // Delete a book
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }

    // Add a book to the book catalog
    public Books addBooks(BookAddRequest bookAddRequest) {
        Books newBooks = new Books();

        int userId = bookAddRequest.getUserId();
        Users addingUser = getUserById(userId);
        int bookId = bookAddRequest.getBookID();

        // check if book with same id exists already
        if (bookRepository.findById(bookId).isEmpty()) {
            if (!addingUser.getIsLibrarian()) {
                System.out.print("User is not a librarian. User doesn't have permission to add Books.");
            } else {
                newBooks.setBookID(bookAddRequest.getBookID());
                newBooks.setAuthor(bookAddRequest.getAuthor());
                newBooks.setIsbn(bookAddRequest.getIsbn());
                newBooks.setTitle(bookAddRequest.getTitle());
                newBooks.setGenre(bookAddRequest.getGenre());
                newBooks.setPublisher(bookAddRequest.getPublisher());
                newBooks.setDateAdded(bookAddRequest.getDateAdded());
                newBooks.setDateModified(bookAddRequest.getDateModified());
                bookRepository.save(newBooks);
            }
        } else {
            System.out.print("Book with id: " + bookId + " exists already");
        }
        return newBooks;
    }

    // Get books checked out by all users
    public List<Rentals> getRentals() {
        return rentalRepository.findAll();
    }

    // Get books checked out by a user
    public List<Rentals> getBooksCheckedOutByUser(int userId) {
        return rentalRepository.getRentalsByUserId(userId);
    }

    // Checkout a book
    public Rentals checkoutBooks(Rentals checkoutRequest) {
        Rentals finalRentalDetails = new Rentals();
        int bookId = checkoutRequest.getBookid();
        Rentals existingRentals = rentalRepository.getRentalsByBookID(bookId);
        if (existingRentals == null) {
            finalRentalDetails = rentalRepository.save(checkoutRequest);
        } else {
            System.out.print("Book with id: " + bookId + " has been checked out already");
        }
        return finalRentalDetails;
    }

    // Return a book
    public void returnBook(int id) {
        rentalRepository.deleteById(id);
    }

    // Update book record by modified date
    public Books updateBookRecord(int id, Books bookDetails) {
        Books book = bookRepository.findById(id).get();

        if (book.getBookID() > 0) {
            book.setDateModified(bookDetails.getDateModified());
        } else {
            System.out.print("Book with id: " + id + " you are trying to update doesn't exist.");
        }
        return bookRepository.save(book);
    }
}
