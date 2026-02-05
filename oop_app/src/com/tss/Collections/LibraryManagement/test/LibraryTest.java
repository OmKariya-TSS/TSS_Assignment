package com.tss.Collections.LibraryManagement.test;

import com.tss.Collections.LibraryManagement.Exceptions.*;
import com.tss.Collections.LibraryManagement.model.Book;
import com.tss.Collections.LibraryManagement.model.Member;
import com.tss.Collections.LibraryManagement.service.LibraryService;
import java.util.InputMismatchException;


import java.util.Scanner;

public class LibraryTest {
    private static Scanner scanner = new Scanner(System.in);
    private static LibraryService service = new LibraryService();
    public static void main(String[] args) {
        System.out.println("Welcome to Library Management System");
        printMenu();
    }
    private static void printMenu(){
        System.out.println("1. Add Book");
        System.out.println("2. Add Book Copy");
        System.out.println("3. Add Member");
        System.out.println("4. Borrow Book Copy");
        System.out.println("5. Return Book Copy");
        System.out.println("6. View book by id");
        System.out.println("7. View All Books");
        System.out.println("8. View All Book Copies");
        System.out.println("9. View All Members");
        System.out.println("10. View Books Borrowed By Member");
        System.out.println("11. Find Borrower of book");
        System.out.println("12. Show all borrowed books");
        System.out.println("0. Exit");
        System.out.println("Enter the choice");
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addBookCopy();
                    break;
                case 3:
                    addMember();
                    break;
                case 4:
                    borrowBookCopy();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    viewBookById();
                    break;
                case 7:
                    viewAllBooks();
                    break;
                case 8:
                    viewAllBookCopies();
                    break;
                case 9:
                    viewAllMembers();
                    break;
                case 10:
                    viewBooksBorrowedByMember();
                    break;
                case 11:
                    findBorrowedMember();
                    break;
                case 12:
                    viewAllBorrowedBooks();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Please enter valid choice");
                    printMenu();
            }
        }catch (InputMismatchException e){
            System.out.println("enter a valid number");
            scanner.nextLine();
            printMenu();
        }
    }

    private static void viewBookById() {
        try {
            System.out.print("Enter Book ID: ");
            int id = scanner.nextInt();

            Book book = service.findBookById(id);
            if (book == null) {
                System.out.println("Book not found");
            } else {
                System.out.println(book);
            }

        }catch (InputMismatchException e){
            System.out.println("enter a valid number");
            scanner.nextLine();
            printMenu();
        }
        catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        printMenu();
    }

    private static void addBook() {
        scanner.nextLine();

        try {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            System.out.print("Enter author: ");
            String author = scanner.nextLine();

            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            Book book = service.addBook(title, author, category);

        }
        catch (BookAlreadyExists | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error occurred");
        }

        printMenu();
    }


    private static void addBookCopy() {
        try {
            System.out.print("Enter Book ID: ");
            int bookId = scanner.nextInt();

            service.addBookCopy(bookId);

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid numeric Book ID.");
            scanner.nextLine();
            addBookCopy();
            return;
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        printMenu();
    }


    private static void addMember() {
        scanner.nextLine();

        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            Member member = service.addMember(name, email);
            System.out.println("Member added with ID: " + member.getId());

        } catch (InvalidEmailException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        printMenu();
    }

    private static void borrowBookCopy() {
        try {
            System.out.print("Enter Copy ID: ");
            int copyId = scanner.nextInt();

            System.out.print("Enter Member ID: ");
            int memberId = scanner.nextInt();

            service.borrowBookCopy(copyId, memberId);
            System.out.println("Book copy borrowed successfully");

        } catch (InputMismatchException e) {
            System.out.println("Please enter valid numeric IDs");
            scanner.nextLine();
            printMenu();
        } catch (BookNotFoundException | BookAlreadyBorrowed | MemberNotFoundException|BookCopyNotFoundException e) {
            System.out.println(e.getMessage());
        }

        printMenu();
    }


    private static void returnBook() {
        try {
            System.out.print("Enter Copy ID: ");
            int copyId = scanner.nextInt();

            service.returnBookCopy(copyId);
            System.out.println("Book copy returned successfully");

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid numeric Copy ID.");
            scanner.nextLine();
            returnBook();
            return;
        } catch (BookCopyNotFoundException | BookNotBorrowedException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        printMenu();
    }


    private static void viewAllBooks(){
        service.viewAllBooks();
        printMenu();
    }
    private static void viewAllBookCopies() {
        try {
            System.out.print("Enter Book ID to show copies: ");
            int id = scanner.nextInt();

            service.viewAllBookCopiesById(id);

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid numeric Book ID.");
            scanner.nextLine();
            viewAllBookCopies();
            return;
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        printMenu();
    }

    private static void viewAllMembers(){
        try{service.viewAllMembers();}
        catch (MemberNotFoundException e){
            System.out.println(e.getMessage());
        }
        printMenu();
    }
    private static void viewBooksBorrowedByMember() {
        try {
            System.out.print("Enter Member ID: ");
            int id = scanner.nextInt();
            service.viewBooksBorrowedByMember(id);
        }catch (InputMismatchException e) {
            System.out.println("Please enter a valid numeric Book ID.");
            scanner.nextLine();
            viewBooksBorrowedByMember();
            return;
        }
        catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        printMenu();
    }
    private static void findBorrowedMember() {
        try {
            System.out.print("Enter Copy ID: ");
            int id = scanner.nextInt();
            System.out.println(service.findMemberByBookCopy(id));
        }catch (InputMismatchException e) {
            System.out.println("Please enter a valid numeric Book ID.");
            scanner.nextLine();
            findBorrowedMember();
            return;
        }
        catch (MemberNotFoundException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        printMenu();
    }

    private static void viewAllBorrowedBooks(){
        service.viewAllBorrowedBooks();
        printMenu();
    }
}
