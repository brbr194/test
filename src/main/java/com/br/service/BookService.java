package com.br.service;

import com.br.dao.BookDao;
import com.br.entity.Admin;
import com.br.entity.Book;
import com.br.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class BookService {
    @Resource
    private BookDao bookDao;


    public PageInfo<Book> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Book> list = bookDao.findBySearch(params);
        return PageInfo.of(list);

    }

    public void add(Book book) {
        bookDao.insertSelective(book);
    }

    public void update(Book book) {
        bookDao.updateByPrimaryKeySelective(book);
    }

    public void delete(Integer id) {
        bookDao.deleteByPrimaryKey(id);
    }

    public List<Book> findAll() {
        return bookDao.findAll();
    }
}
