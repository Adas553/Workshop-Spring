package pl.coderslab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.pojoClass.Book;
import pl.coderslab.service.BookService;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/books")
public class ManageBookController {

    private final Logger logger = LoggerFactory.getLogger(ManageBookController.class);
    private final BookService bookService;

    public ManageBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public String showBooks(Model model) {
        List<Book> books = bookService.getBookList();
        model.addAttribute("books", books);
        return "/books/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "/books/add";
    }

    @PostMapping("/add")
    public String postAddForm(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "/books/add";
        }
        bookService.add(book);
        return "redirect:/admin/books/all";
    }

    @GetMapping("/update/{id}")
    public String updateBookForm(Model model, @PathVariable long id) {
        model.addAttribute("book", bookService.get(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }));
        return "/books/update";
    }

    @PostMapping("/update/{id}")
    public String postUpdateBookForm(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "books/update";
        }
        bookService.update(book);
        return "redirect:/admin/books/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteBookForm(@PathVariable long id, Model model) {
        Book book = bookService.get(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
        bookService.delete(book);
        return "redirect:/admin/books/all";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable long id, Model model) {
        model.addAttribute("book", bookService.get(id).orElseThrow(() -> {
            throw new EntityNotFoundException();
        }));
        return "/books/single";
    }


    @RequestMapping(value = "/test-model")
    @ResponseBody
    public void getAllFromMap(Model model) {
        model.asMap().forEach((k, v) -> logger.debug(k + ": " + v));
    }

}
