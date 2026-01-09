//package com.lms.main;
//
//import com.lms.model.Book;
//import com.lms.model.Member;
//import com.lms.service.LibraryService;
//import com.lms.exception.*;
//
//import java.util.Scanner;
//
//public class LibraryApp {
//
//  public static void main(String[] args) {
//    LibraryService service = new LibraryService();
//    Scanner sc = new Scanner(System.in);
//
//    // Seed data
//    service.addBook(new Book("101", "Clean Code"));
//    service.addBook(new Book("102", "Effective Java"));
//    service.addMember(new Member(1, "Sam"));
//
//    try {
//      service.issueBook("101");
//      service.showAllBooks();
//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//    }
//
//    sc.close();
//  }
//}

package com.lms.main;

import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.service.LibraryService;
import com.lms.exception.*;

import java.util.Scanner;

public class LibraryApp {

  private static LibraryService service = new LibraryService();
  private static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    seedData(); // optional, adds sample books/members
    boolean exit = false;

    while (!exit) {
      printMenu();
      int choice = getIntInput("Enter your choice: ");

      switch (choice) {
        case 1 -> addBook();
        case 2 -> removeBook();
        case 3 -> showAllBooks();
        case 4 -> registerMember();
        case 5 -> issueBook();
        case 6 -> returnBook();
        case 7 -> exit = true;
        default -> System.out.println("Invalid choice. Try again.");
      }
      System.out.println();
    }

    System.out.println("Exiting Library Management System. Goodbye!");
    sc.close();
  }

  // ---------- MENU DISPLAY ----------
  private static void printMenu() {
    System.out.println("===== LIBRARY MENU =====");
    System.out.println("1. Add Book");
    System.out.println("2. Remove Book");
    System.out.println("3. Show All Books");
    System.out.println("4. Register Member");
    System.out.println("5. Issue Book");
    System.out.println("6. Return Book");
    System.out.println("7. Exit");
  }

  // ---------- HELPER METHODS ----------
  private static int getIntInput(String prompt) {
    System.out.print(prompt);
    while (!sc.hasNextInt()) {
      sc.next(); // discard invalid input
      System.out.print("Invalid input. " + prompt);
    }
    return sc.nextInt();
  }

  private static String getStringInput(String prompt) {
    System.out.print(prompt);
    sc.nextLine(); // consume leftover newline
    return sc.nextLine();
  }

  // ---------- MENU ACTIONS ----------
  private static void addBook() {
    String isbn = getStringInput("Enter ISBN: ");
    String title = getStringInput("Enter Title: ");
    Book book = new Book(isbn, title);
    service.addBook(book);
    System.out.println("Book added successfully!");
  }

  private static void removeBook() {
    String isbn = getStringInput("Enter ISBN to remove: ");
    try {
      service.removeBook(isbn);
      System.out.println("Book removed successfully!");
    } catch (BookNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void showAllBooks() {
    System.out.println("----- All Books -----");
    service.showAllBooks();
  }

  private static void registerMember() {
    int id = getIntInput("Enter Member ID: ");
    String name = getStringInput("Enter Member Name: ");
    Member member = new Member(id, name);
    service.addMember(member);
    System.out.println("Member registered successfully!");
  }

  private static void issueBook() {
    String isbn = getStringInput("Enter ISBN to issue: ");
    try {
      service.issueBook(isbn);
      System.out.println("Book issued successfully!");
    } catch (BookNotFoundException | BookAlreadyIssuedException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void returnBook() {
    String isbn = getStringInput("Enter ISBN to return: ");
    try {
      service.returnBook(isbn);
      System.out.println("Book returned successfully!");
    } catch (BookNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  // ---------- OPTIONAL SEED DATA ----------
  private static void seedData() {
    service.addBook(new Book("101", "Clean Code"));
    service.addBook(new Book("102", "Effective Java"));
    service.addMember(new Member(1, "Sam"));
    service.addMember(new Member(2, "Alice"));
  }
}
