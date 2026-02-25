package com.tss.LibraryManagement.service;

import com.tss.LibraryManagement.Exceptions.*;
import com.tss.LibraryManagement.model.Book;
import com.tss.LibraryManagement.model.BookCopy;
import com.tss.LibraryManagement.model.Member;


public class LibraryService {

    private final BookService bookService;
    private final MemberService memberService;
    private final BorrowingService borrowingService;

    public LibraryService() {
        bookService = new BookService();
        memberService = new MemberService();
        borrowingService = new BorrowingService();
    }

    public Book addBook(String title, String author, String category) throws BookAlreadyExists {
        return bookService.addBook(title, author, category);
    }

    public void addBookCopy(int bookId) {
        bookService.addBookCopy(bookId);
    }

    public Book findBookById(int id) {
        return bookService.findBookById(id);
    }

    public void viewAllBooks() {
        bookService.viewAllBooks(borrowingService.getBorrowedCopies());
    }

    public void viewAllBookCopiesById(int bookId) {
        bookService.viewAllBookCopiesById(bookId);
    }

    public Member addMember(String name, String email) throws InvalidEmailException {
        return memberService.addMember(name, email);
    }

    public void viewAllMembers() {
        memberService.viewAllMembers();
    }

    public void borrowBookCopy(int copyId, int memberId) {
        if (bookService.getBookCopiesById().isEmpty()) {
            throw new BookCopyNotFoundException("Book copies not found");
        }
        if (memberService.getMembersById().isEmpty()) {
            throw new MemberNotFoundException("Member not found");
        }
        if (copyId <= 0 || memberId <= 0) {
            throw new IllegalArgumentException("Invalid copy ID or member ID");
        }

        BookCopy copy = bookService.findCopyById(copyId);
        if (copy == null) {
            throw new BookNotFoundException("Book copy with ID " + copyId + " not found");
        }

        Member member = memberService.findMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member with ID " + memberId + " not found");
        }

        borrowingService.borrowBookCopy(copy, member);
    }

    public void returnBookCopy(int copyId) {
        if (bookService.getBookCopiesById().isEmpty()) {
            throw new BookNotFoundException("Books not found");
        }
        if (copyId <= 0) {
            throw new IllegalArgumentException("Invalid copy ID");
        }

        BookCopy copy = bookService.findCopyById(copyId);
        if (copy == null) {
            throw new BookNotFoundException("Book copy with ID " + copyId + " not found");
        }

        borrowingService.returnBookCopy(copy);
    }

    public Member findMemberByBookCopy(int copyId) {
        if (bookService.getBookCopiesById().isEmpty()) {
            throw new BookCopyNotFoundException("Book copy not found");
        }
        if (copyId <= 0) {
            throw new IllegalArgumentException("Invalid copy ID");
        }

        BookCopy copy = bookService.findCopyById(copyId);
        if (copy == null) {
            throw new BookNotFoundException("Book copy with ID " + copyId + " not found");
        }

        return borrowingService.findMemberByBookCopy(copy);
    }

    public void viewBooksBorrowedByMember(int memberId) {
        if (memberService.getMembersById().isEmpty()) {
            throw new MemberNotFoundException("Member not found");
        }
        if (memberId <= 0) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        Member member = memberService.findMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member with ID " + memberId + " not found");
        }

        borrowingService.viewBooksBorrowedByMember(member);
    }

    public void viewAllBorrowedBooks() {
        borrowingService.viewAllBorrowedBooks();
    }
}