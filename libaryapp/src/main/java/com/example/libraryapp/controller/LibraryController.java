package com.example.libraryapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.example.libraryapp.model.*;
import com.example.libraryapp.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:8081")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    // API to get user by user id
    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public Users getUserById(@PathVariable(value = "id") int id) {
        return libraryService.getUserById(id);
    }

    // API to get the list of all users
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<Users> getUsers() {
        return libraryService.getUsers();
    }

    // API to login with the credentials
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public Users loginUser(@RequestBody Login loginDetails) throws NullPointerException {
        String userName = loginDetails.getUsername();
        System.out.print("Username at controller: " + userName);
        return libraryService.loginUser(loginDetails);
    }

    // API to register or signup user with the library app
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public Users registerUser(@RequestBody Register userDetails) throws Exception {
        return libraryService.registerUser(userDetails);
    }

    // API to delete the user by user id
    @RequestMapping(value = "/users/{userId}", method=RequestMethod.DELETE)
    public void deleteUserById(@PathVariable(value = "userId") int id) {
        libraryService.deleteUserById(id);
    }

    // API to update the user details/records
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
    public Users updateUser(@PathVariable(value = "userId") int id, @RequestBody Users userDetails) {
        return libraryService.updateUser(id, userDetails);
    }

    // API to get the list of books in the library
    @RequestMapping(value="/books", method=RequestMethod.GET)
    public List<Books> getBooks() {
        return libraryService.getBooks();
    }

    // API to get the book by book id
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public Books getBookById(@PathVariable(value = "id") int id) {
        return libraryService.getBookById(id);
    }

    // API to get the book by its title
    @RequestMapping(value="/books/title/{title}", method = RequestMethod.GET)
    public Books getBookByTitle(@PathVariable(value = "title") String title) throws UnsupportedEncodingException {
        System.out.print("Title is: " + title);
        String finalTitle = URLDecoder.decode(title, "UTF-8");
        System.out.print("Decoded title is: " + finalTitle);
        return libraryService.getBookByTitle(finalTitle);
    }

    // API to add the books to the book catalog by librarian
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public Books addBooks(@RequestBody BookAddRequest bookAddRequest) {
        Books book = libraryService.addBooks(bookAddRequest);
        return book;
    }

    // API to get the books checked out by all users
    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public List<Rentals> getRentals() {
        return libraryService.getRentals();
    }

    // API to get the books checked out by user
    @RequestMapping(value = "/rentals/users/{userId}", method = RequestMethod.GET)
    public List<Rentals> getBookRentalsForUser(@PathVariable(value = "userId") int userId) {
        return libraryService.getBooksCheckedOutByUser(userId);
    }

    // API to checkout a book for a user
    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    public Rentals checkoutBooks(@RequestBody Rentals checkoutRequest) {
        return libraryService.checkoutBooks(checkoutRequest);
    }

    // API to return a book for a user
    @RequestMapping(value = "/rentals/{rentalId}", method = RequestMethod.DELETE)
    public void returnBook(@PathVariable(value = "rentalId") int id) {
        libraryService.returnBook(id);
    }

    // API to delete book from a book catalog
    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.DELETE)
    public void deleteBookById(@PathVariable(value = "bookId") int id) {
        libraryService.deleteBookById(id);
    }

    // API to update the book details
    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.PUT)
    public Books updateBookRecord(@PathVariable(value = "bookId") int id, @RequestBody Books bookDetails) {
        return libraryService.updateBookRecord(id, bookDetails);
    }

}
