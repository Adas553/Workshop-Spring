package pl.coderslab.pojoClass;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> get(Long id);
    List<Book> getBookList();
    void add(Book book);
    void update(Book book);
    void delete(Book book);
}
