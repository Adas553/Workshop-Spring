package pl.coderslab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.pojoClass.Book;
import pl.coderslab.pojoClass.BookService;
import pl.coderslab.pojoClass.MemoryBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private final Logger logger = LoggerFactory.getLogger(toString().getClass().getSimpleName());

    public BookController(MemoryBookService memoryBookService) {
        this.bookService = memoryBookService;
    }

    @GetMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping
    public List displayBooks() {
        return bookService.getBookList();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        try {
            return bookService.get(id);
        } catch (IndexOutOfBoundsException e) {
            return new Book();
        }
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
         bookService.add(book);
    }

    @PutMapping
    public void updateBook(Book book) {
        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        try {
            bookService.delete(id);
        } catch (IndexOutOfBoundsException e) {
            logger.info("Given book does not exits");
        }
    }

}
