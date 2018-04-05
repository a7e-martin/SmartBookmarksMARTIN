package org.diiage.martin.smartbookmarksmartin.models;

public class Comment {
    Long id;
    Long pageNumber;
    Long bookId;
    String comment;
    String date;
    Book book;

    public Comment(){}

    public Comment(Long bookId, Long pageNumber, String comment) {
        this.bookId = bookId;
        this.pageNumber = pageNumber;
        this.comment = comment;
    }

    public Comment(Long id, Long bookId, Long pageNumber, String comment, String date) {
        this.id = id;
        this.bookId = bookId;
        this.pageNumber = pageNumber;
        this.comment = comment;
        this.date = date;
    }

    public Comment(Long id, Long bookId, String comment, String date, Book book) {
        this.id = id;
        this.bookId = bookId;
        this.comment = comment;
        this.date = date;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }
}
