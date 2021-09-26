package com.demo.service.impl;

import com.demo.dao.BookDao;
import com.demo.pojo.Book;
import com.demo.pojo.Page;
import com.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao ;

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void delBookById(Integer id) {
        bookDao.delBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();



        page.setPageSize(pageSize);

        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        page.setPageNO(pageNo);
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }

        page.setPageTotal(pageTotal);
        //当前页数据开始的索引
        int begin = (page.getPageNO() - 1) * pageSize;

        List<Book> items = bookDao.queryForPageItems(begin, pageSize);

        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPince(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);

        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        page.setPageNO(pageNo);
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }

        page.setPageTotal(pageTotal);
        //当前页数据开始的索引
        int begin = (page.getPageNO() - 1) * pageSize;

        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);

        page.setItems(items);
        return page;
    }
}
