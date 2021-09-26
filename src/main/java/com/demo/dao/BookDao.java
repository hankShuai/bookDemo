package com.demo.dao;

import com.demo.pojo.Book;

import java.util.List;

public interface BookDao {

    int addBook(Book book);

    int delBookById(Integer id);

    int updateBook(Book book);

    Book queryBookById(Integer bookId);

    List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
