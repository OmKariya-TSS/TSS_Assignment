package com.tss.LibraryManagement.model;

import com.tss.LibraryManagement.model.Book;

public class BookCopy {

    private static int copyIdCounter = 1;

    private final int copyId;
    private final Book book;
    private boolean borrowed;

    public BookCopy(Book book) {
        this.copyId = copyIdCounter++;
        this.book = book;
        this.borrowed = false;
    }

    @Override
    public String toString() {
        return String.format(
                """
                -------------------------
                Copy ID   : %d
                Book ID   : %d
                Title     : %s
                Status    : %s
                -------------------------
                """,
                copyId,
                book.getId(),
                book.getTitle(),
                borrowed ? "BORROWED" : "AVAILABLE"
        );
    }


    public int getCopyId() {
        return copyId;
    }

    public Book getBook() {
        return book;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void borrow() {
        this.borrowed = true;
    }

    public void returned() {
        this.borrowed = false;
    }
}
