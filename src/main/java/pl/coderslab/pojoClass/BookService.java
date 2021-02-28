package pl.coderslab.pojoClass;

import java.util.List;

public interface BookService {

    Book get(Long id);
    List<Book> getBookList();
    void add(Book book);
    void update(Book book);
    void delete(Long id);
}
