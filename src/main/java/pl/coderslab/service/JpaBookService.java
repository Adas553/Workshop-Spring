package pl.coderslab.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.coderslab.pojoClass.Book;
import pl.coderslab.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class JpaBookService implements BookService {

    private final BookRepository bookRepository;

    public JpaBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> get(Long id) {
        return bookRepository.findById(id);
    }
    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }
    @Override
    public void delete(Book book) {
        bookRepository.deleteById(book.getId());
    }
    @Override
    public void update(Book book) {
        bookRepository.save(book);
    }
}
