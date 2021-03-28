package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.pojoClass.Book;
import pl.coderslab.pojoClass.BookService;
import pl.coderslab.repository.BookRepository;


import java.util.List;

@Controller
@RequestMapping("/admin/books")
public class ManageBookController {

    private final BookService bookService;

    public ManageBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public String showPosts(Model model) {
        List<Book> books = bookService.getBookList();
        model.addAttribute("books", books);
        return "/books/all";
    }

    @GetMapping("/create")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add";
    }


}
