package com.mu.bookmanagement.service;

import com.mu.bookmanagement.entity.BookEntity;
import com.mu.bookmanagement.entity.ResultEntity;
import com.mu.bookmanagement.exception.SystemException;
import com.mu.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mu.bookmanagement.enums.ExpectionEnum;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 获取所有图书服务
     * @return
     */
    public ResultEntity<List<BookEntity>> getAllBooks () {
        List<BookEntity> bookEntities = bookRepository.findAll();
        return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),"获取所有书籍成功",bookEntities);
    }

    /**
     * 新增图书服务
     * @param book
     * @return
     */
    public ResultEntity<BookEntity> createBook (BookEntity book) {
        BookEntity bookEntity = bookRepository.save(book);
        if (bookEntity != null) {
            return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),"添加成功",null);
        } else {
            throw new RuntimeException("未知错误");
        }
    }

    /**
     * 修改图书服务
     * @param book
     * @return
     */
    public ResultEntity<BookEntity> updateBook (BookEntity book) {
        BookEntity bookEntity = bookRepository.findByIsbn(book.getISBN());
        if (bookEntity != null) {
            book.setId(bookEntity.getId());
            if (bookRepository.save(book) != null) {
                return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),"修改成功",null);
            } else {
                throw new RuntimeException("未知错误");
            }
        } else {
            throw new SystemException(ExpectionEnum.BOOK_NOT_EXISTS.getCode(),ExpectionEnum.BOOK_NOT_EXISTS.getMsg());
        }
    }

    /**
     * 删除图书服务
     * @param book
     * @return
     */
    public ResultEntity<BookEntity> deleteBook (BookEntity book) {
        BookEntity bookEntity = bookRepository.findByIsbn(book.getISBN());
        if (bookEntity != null) {
            bookRepository.deleteById(bookEntity.getId());
            return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),"删除成功", null);
        } else {
            throw new SystemException(ExpectionEnum.BOOK_NOT_EXISTS.getCode(),ExpectionEnum.BOOK_NOT_EXISTS.getMsg());
        }
    }

    /**
     * 搜索图书服务
     * @param type
     * @param keyWord
     * @return
     */
    public ResultEntity<List<BookEntity>> findBook (String type, String keyWord) {
        List<BookEntity> list;
        switch (type) {
            case "ISBN":
                list = bookRepository.findAllByIsbn(keyWord);
                break;
            case "name":
                list = bookRepository.findAllByName(keyWord);
                break;
            case "author":
                list = bookRepository.findAllByAuthor(keyWord);
                break;
            case "description":
                list = bookRepository.findAllByDescription(keyWord);
                break;
            default:
                list = null;
                break;
        }
        if (list != null) {
            return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),ExpectionEnum.SUCCESS.getMsg(), list);
        } else {
            throw new SystemException(ExpectionEnum.SEARCH_TYPE_NOT_VAILD.getCode(),ExpectionEnum.SEARCH_TYPE_NOT_VAILD.getMsg());
        }
    }
}
