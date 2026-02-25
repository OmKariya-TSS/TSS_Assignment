package com.tss.LibraryManagement.service;


import com.tss.LibraryManagement.Exceptions.*;
import com.tss.LibraryManagement.model.BookCopy;
import com.tss.LibraryManagement.model.Member;

import java.util.HashMap;
import java.util.Map;

public class BorrowingService {

    private final Map<BookCopy, Member> borrowedCopies;

    public BorrowingService() {
        borrowedCopies = new HashMap<>();
    }

    public void borrowBookCopy(BookCopy copy, Member member) {
        if (copy.isBorrowed()) {
            throw new BookAlreadyBorrowed("Book copy already borrowed");
        }
        copy.borrow();
        borrowedCopies.put(copy, member);
    }

    public void returnBookCopy(BookCopy copy) {
        if (!copy.isBorrowed()) {
            throw new BookNotBorrowedException("Book copy is not currently borrowed");
        }
        copy.returned();
        borrowedCopies.remove(copy);
    }

    public Member findMemberByBookCopy(BookCopy copy) {
        Member member = borrowedCopies.get(copy);
        if (member == null) {
            throw new BookNotBorrowedException("Book copy is not borrowed");
        }
        return member;
    }

    public Map<BookCopy, Member> getBorrowedCopies() {
        return borrowedCopies;
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
            System.out.println("Copy ID     : " + copy.getCopyId());
            System.out.println("Book        : " + copy.getBook().getTitle());
            System.out.println("Author      : " + copy.getBook().getAuthor());
            System.out.println("Borrowed By : " + member.getName() + " (Member ID: " + member.getId() + ")");
            System.out.println("-------------------------------");
        }
    }

    public void viewBooksBorrowedByMember(Member member) {
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
}