package pl.coderslab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.pojoClass.Book;
import pl.coderslab.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final Logger logger = LoggerFactory.getLogger(toString().getClass().getSimpleName());

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
//    public BookController(MemoryBookService memoryBookService) {
//        this.bookService = memoryBookService;
//    }

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
        return bookService.get(id).orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        });
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
         bookService.add(book);
    }

    @PutMapping
    public void updateBook(@RequestBody Book book) {
        bookService.update(book);
    }

    @DeleteMapping()
    public void deleteBook(@RequestBody Book book) {
            bookService.delete(book);
    }

}
