package com.lms.model;


public class Book {
  private String isbn;
  private String title;
  private boolean issued;

  public Book(String isbn, String title) {
    this.isbn = isbn;
    this.title = title;
    this.issued = false;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public boolean isIssued() {
    return issued;
  }

  public void issue() {
    this.issued = true;
  }

  public void returnBook() {
    this.issued = false;
  }

  @Override
  public String toString() {
    return isbn + " | " + title + " | Issued: " + issued;
  }
}