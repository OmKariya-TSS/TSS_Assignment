package com.tss.Collections.v1.service;

import com.tss.Collections.v1.model.BookCopy;
import com.tss.Collections.v1.model.Member;
import com.tss.Collections.v1.Exceptions.*;
import com.tss.Collections.v1.model.Book;

import java.util.HashMap;
import java.util.Map;

public class LibraryService {

    private Map<Integer, Book> booksById;
    private Map<String, Integer> categoryIndex;

    private Map<Integer, Member> membersById;
    private Map<String, Integer> emailIndex;

    private Map<Integer, BookCopy> bookCopiesById;
    private Map<BookCopy, Member> borrowedCopies;

    public LibraryService() {
        booksById = new HashMap<>();
        categoryIndex = new HashMap<>();

        membersById = new HashMap<>();
        emailIndex = new HashMap<>();

        bookCopiesById = new HashMap<>();
        borrowedCopies = new HashMap<>();
    }

    public Book addBook(String title, String author, String category)
            throws BookAlreadyExists {

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

        if(booksById.isEmpty()){
            throw new BookNotFoundException("Book list is empty please make books");
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


    public Member addMember(String name, String email)
            throws InvalidEmailException {

        if (name == null || email == null) {
            throw new IllegalArgumentException("Member fields cannot be null");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new InvalidEmailException("Invalid email format");
        }

        if (emailIndex.containsKey(email)) {
            throw new InvalidEmailException("Email already exists");
        }

        Member member = new Member(name, email);
        membersById.put(member.getId(), member);
        emailIndex.put(email, member.getId());

        return member;
    }

    public void borrowBookCopy(int copyId, int memberId) {

        if(bookCopiesById.isEmpty()){
            throw new BookCopyNotFoundException("Book copies not found");
        }
        if(membersById.isEmpty()){
            throw new MemberNotFoundException("Member not found");
        }
        if (copyId <= 0 || memberId <= 0) {
            throw new IllegalArgumentException("Invalid copy ID or member ID");
        }

        BookCopy copy = bookCopiesById.get(copyId);
        if (copy == null) {
            throw new BookNotFoundException("Book copy with ID " + copyId + " not found");
        }

        if (copy.isBorrowed()) {
            throw new BookAlreadyBorrowed("Book copy already borrowed");
        }

        Member member = membersById.get(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member with ID " + memberId + " not found");
        }

        copy.borrow();
        borrowedCopies.put(copy, member);
    }


    public void returnBookCopy(int copyId) {

        if(bookCopiesById.isEmpty()){
            throw new BookNotFoundException("Books not found ");
        }
        if (copyId <= 0) {
            throw new IllegalArgumentException("Invalid copy ID");
        }

        BookCopy copy = bookCopiesById.get(copyId);
        if (copy == null) {
            throw new BookNotFoundException("Book copy with ID " + copyId + " not found");
        }

        if (!copy.isBorrowed()) {
            throw new BookNotBorrowedException("Book copy is not currently borrowed");
        }

        copy.returned();
        borrowedCopies.remove(copy);
    }

    public void viewAllBooks() {

        if (booksById.isEmpty()) {
            System.out.println("No books available");
            return;
        }

        for (Book book : booksById.values()) {

            System.out.println(book);

            boolean hasCopy = false;
            System.out.println("Copies : ");
            for (BookCopy copy : bookCopiesById.values()) {
                if (copy.getBook().equals(book)) {
                    System.out.println("   -> " + copy);
                    hasCopy = true;
                }
            }

            System.out.println("-----------------------------------");
        }
    }


    public void viewAllMembers() {
        if(membersById.isEmpty()){
            throw new MemberNotFoundException("No members found");
        }
        for (Member member : membersById.values()) {
            System.out.println(member);
        }
    }

    public void viewAllBookCopiesById(int bookId) {

        if(bookCopiesById.isEmpty()){
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
    public Book findBookById(int id){
        if(booksById.isEmpty()){
            throw new BookNotFoundException("Books not found");
        }
        return booksById.get(id);
    }


    public void viewBooksBorrowedByMember(int memberId) {

        if(membersById.isEmpty()){
            throw new MemberNotFoundException("Member not found");
        }
        if (memberId <= 0) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        Member member = membersById.get(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member with ID " + memberId + " not found");
        }

        boolean found = false;
        for (Map.Entry<BookCopy, Member> entry : borrowedCopies.entrySet()) {
            if (entry.getValue().equals(member)) {
                System.out.println(entry.getKey());
                found = true;
            }
        }

        if (!found) {
            System.out.println("This member has not borrowed any books");
        }
    }

    public void viewAllBorrowedBooks() {
        if (borrowedCopies.isEmpty()) {
            System.out.println("No books are currently borrowed");
            return;
        }
        System.out.println("Borrowed Books:");
        System.out.println("-------------------------------");

        for (Map.Entry<BookCopy, Member> entry : borrowedCopies.entrySet()) {
            BookCopy copy = entry.getKey();
            Member member = entry.getValue();

            System.out.println("Copy ID : " + copy.getCopyId());
            System.out.println("Book    : " + copy.getBook().getTitle());
            System.out.println("Author  : " + copy.getBook().getAuthor());
            System.out.println("Borrowed By : " + member.getName()
                    + " (Member ID: " + member.getId() + ")");
            System.out.println("-------------------------------");
        }
    }


    public Member findMemberByBookCopy(int copyId) {

        if(bookCopiesById.isEmpty()){
            throw new BookCopyNotFoundException("Book copy noy found");
        }
        if (copyId <= 0) {
            throw new IllegalArgumentException("Invalid copy ID");
        }

        BookCopy copy = bookCopiesById.get(copyId);
        if (copy == null) {
            throw new BookNotFoundException(
                    "Book copy with ID " + copyId + " not found");
        }

        Member member = borrowedCopies.get(copy);
        if (member == null) {
            throw new BookNotBorrowedException("Book copy is not borrowed");
        }

        return member;
    }

}
