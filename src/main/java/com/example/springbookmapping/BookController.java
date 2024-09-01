package com.example.springbookmapping;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    List<Book> books = new ArrayList<Book>();
    int bookID = 1;

    @GetMapping
    public List<Book> getBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return books.get(id);
    }

    @GetMapping("/bookinfo")
    public List<Book> getBookInfo(@RequestParam String title , @RequestParam String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getPublisher().equals(publisher)) {
                result.add(book);
            }
        }
        return result;
    }

    @PostMapping("/create")
    public Book addBook(@RequestBody Book book) {
        book.setId(bookID++);
        books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        for (Book book1 : books) {
            if (book1.getId() == id) {
                book1.setTitle(book.getTitle());
                book1.setPublisher(book.getPublisher());
                book1.setAuthor(book.getAuthor());
                return book1;
            }
        }
        return null;
    }

    @DeleteMapping("/delete")
    public void deleteBook(@PathVariable int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                books.remove(book);
            }
        }

//        books.removeIf(book -> book.getId() == id);
    }
}
