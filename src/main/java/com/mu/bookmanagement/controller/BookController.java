package com.mu.bookmanagement.controller;

import com.mu.bookmanagement.entity.BookEntity;
import com.mu.bookmanagement.entity.ResultEntity;
import com.mu.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 获取所有图书接口
     * @return
     */
    @GetMapping("/getAllBooks")
    public ResultEntity<List<BookEntity>> getAllBooks () {
        return bookService.getAllBooks();
    }

    /**
     * 新增图书接口
     * @param isbn
     * @param name
     * @param author
     * @param description
     * @return
     */
    @PostMapping("/createBook")
    public ResultEntity<BookEntity> createBook (@RequestParam String isbn, @RequestParam String name, @RequestParam String author, @RequestParam String description) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBN(isbn);
        bookEntity.setAuthor(author);
        bookEntity.setDescription(description);
        bookEntity.setName(name);
        bookEntity.setCount("0");
        return bookService.createBook(bookEntity);
    }

    /**
     * 修改图书接口
     * @param isbn
     * @param name
     * @param author
     * @param description
     * @return
     */
    @PostMapping("/updateBook")
    public ResultEntity<BookEntity> updateBook (@RequestParam String isbn, @RequestParam String name, @RequestParam String author, @RequestParam String description) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(name);
        bookEntity.setCount("0");
        bookEntity.setDescription(description);
        bookEntity.setISBN(isbn);
        bookEntity.setAuthor(author);
        return bookService.updateBook(bookEntity);
    }

    /**
     * 删除图书接口
     * @param isbn
     * @return
     */
    @PostMapping("/deleteBook")
    public ResultEntity<BookEntity> deleteBook (@RequestParam String isbn) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setISBN(isbn);
        return bookService.deleteBook(bookEntity);
    }

    /**
     * 搜索图书接口
     * @param type
     * @param keyword
     * @return
     */
    @PostMapping("/searchBook")
    public ResultEntity<List<BookEntity>> searchBook (@RequestParam String type, @RequestParam String keyword) {
        return bookService.findBook(type, keyword);
    }
}
