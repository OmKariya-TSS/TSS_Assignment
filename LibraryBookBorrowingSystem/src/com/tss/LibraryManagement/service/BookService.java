package com.tss.LibraryManagement.service;


import com.tss.LibraryManagement.Exceptions.*;
import com.tss.LibraryManagement.model.Book;
import com.tss.LibraryManagement.model.BookCopy;

import java.util.HashMap;
import java.util.Map;

public class BookService {

    private final Map<Integer, Book> booksById;
    private final Map<String, Integer> categoryIndex;
    private final Map<Integer, BookCopy> bookCopiesById;

    public BookService() {
        booksById = new HashMap<>();
        categoryIndex = new HashMap<>();
        bookCopiesById = new HashMap<>();
    }

    public Book addBook(String title, String author, String category) throws BookAlreadyExists {
        if (title == null || author == null || category == null) {
            throw new IllegalArgumentException("Book fields cannot be null");
        }
        category = category.trim().toLowerCase();
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty or blank");
        }
        if (category.length() < 3 || category.length() > 30) {
            throw new IllegalArgumentException("Category must be between 3 and 30 characters");
        }
        if (!category.matches("[a-z ]+")) {
            throw new IllegalArgumentException("Category can contain only letters and spaces");
        }
        if (categoryIndex.containsKey(category)) {
            throw new BookAlreadyExists("Book with category '" + category + "' already exists");
        }

        Book book = new Book(title.trim(), author.trim(), category);
        booksById.put(book.getId(), book);
        categoryIndex.put(category, book.getId());

        BookCopy copy = new BookCopy(book);
        bookCopiesById.put(copy.getCopyId(), copy);

        System.out.println("Book added with ID: " + book.getId());
        System.out.println("Default copy created with Copy ID: " + copy.getCopyId());
        return book;
    }

    public void addBookCopy(int bookId) {
        if (booksById.isEmpty()) {
            throw new BookNotFoundException("Book list is empty, please add books first");
        }
        if (bookId <= 0) {
            throw new IllegalArgumentException("Invalid book ID");
        }
        Book book = booksById.get(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found");
        }
        BookCopy copy = new BookCopy(book);
        bookCopiesById.put(copy.getCopyId(), copy);
        System.out.println("Additional copy added. Copy ID: " + copy.getCopyId());
    }

    public Book findBookById(int id) {
        if (booksById.isEmpty()) {
            throw new BookNotFoundException("Books not found");
        }
        return booksById.get(id);
    }

    public BookCopy findCopyById(int copyId) {
        return bookCopiesById.get(copyId);
    }

    public Map<Integer, Book> getBooksById() {
        return booksById;
    }

    public Map<Integer, BookCopy> getBookCopiesById() {
        return bookCopiesById;
    }

    public void viewAllBooks(Map<BookCopy, ?> borrowedCopies) {
        if (booksById.isEmpty()) {
            System.out.println("No books available");
            return;
        }
        for (Book book : booksById.values()) {
            System.out.println(book);
            System.out.println("Copies : ");
            for (BookCopy copy : bookCopiesById.values()) {
                if (copy.getBook().equals(book)) {
                    System.out.println(" -> " + copy);
                }
            }
            System.out.println("-----------------------------------");
        }
    }

    public void viewAllBookCopiesById(int bookId) {
        if (bookCopiesById.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }
        Book book = booksById.get(bookId);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        boolean found = false;
        for (BookCopy copy : bookCopiesById.values()) {
            if (copy.getBook().equals(book)) {
                System.out.println(copy);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No copies available for this book");
        }
    }
}