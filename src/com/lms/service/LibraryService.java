package com.lms.service;

import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.exception.*;

import java.util.HashMap;
import java.util.Map;

public class LibraryService {

  private Map<String, Book> books = new HashMap<>();
  private Map<Integer, Member> members = new HashMap<>();

  // BOOK OPERATIONS
  public void addBook(Book book) {
    books.put(book.getIsbn(), book);
  }

  public void removeBook(String isbn) throws BookNotFoundException {
    if (!books.containsKey(isbn))
      throw new BookNotFoundException("Book not found: " + isbn);

    books.remove(isbn);
  }

  public void issueBook(String isbn)
    throws BookNotFoundException, BookAlreadyIssuedException {

    Book book = books.get(isbn);

    if (book == null)
      throw new BookNotFoundException("Book not found");

    if (book.isIssued())
      throw new BookAlreadyIssuedException("Book already issued");

    book.issue();
  }

  public void returnBook(String isbn) throws BookNotFoundException {
    Book book = books.get(isbn);

    if (book == null)
      throw new BookNotFoundException("Book not found");

    book.returnBook();
  }

  public void showAllBooks() {
    books.values().forEach(System.out::println);
  }

  // MEMBER OPERATIONS
  public void addMember(Member member) {
    members.put(member.getId(), member);
  }

  public Member getMember(int id) throws MemberNotFoundException {
    if (!members.containsKey(id))
      throw new MemberNotFoundException("Member not found: " + id);

    return members.get(id);
  }
}
