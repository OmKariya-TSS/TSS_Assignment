package com.tss.Collections.LibraryManagement.model;



public class Book {

    private static int idCounter = 1;
    private final int id;
    private String title;
    private String author;
    private final String category;

    public Book(String title, String author, String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return category.equals(book.category);
    }

    @Override
    public String toString() {
        return String.format(
                """
                -------------------------
                Book ID   : %d
                Title     : %s
                Author    : %s
                Category  : %s
                -------------------------
                """,
                id, title, author, category
        );
    }

}
