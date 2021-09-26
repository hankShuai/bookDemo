package com.demo.service;

import com.demo.pojo.Book;
import com.demo.pojo.Page;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    void delBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPince(int pageNo, int pageSize, int min, int max);
}
