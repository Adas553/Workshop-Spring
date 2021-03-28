package pl.coderslab.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.coderslab.pojoClass.Book;
import pl.coderslab.pojoClass.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
//@Primary
public class MemoryBookService implements BookService {

    private static Long nextId = 4L;
    private List<Book> bookList;
    private final Logger logger = LoggerFactory.getLogger(toString().getClass().getSimpleName());


    public MemoryBookService() {
        bookList = new ArrayList<>();
        bookList.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        bookList.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        bookList.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    public static Long getNextId() {
        return nextId;
    }

    public static void setNextId(Long nextId) {
        MemoryBookService.nextId = nextId;
    }

    @Override
    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public Optional<Book> get(Long id) throws IndexOutOfBoundsException {
        return bookList
                .stream()
                .filter(x -> x.getId().equals(id))
                .findAny();
    }

    @Override
    public void add(Book book) {
        book.setId(nextId++);
        bookList.add(book);
    }

    @Override
    public void update(Book book) {

//        if (this.get(book.getId()).isPresent()) {
//            int indexOf = bookList.indexOf(this.get(book.getId()).get());
//            bookList.set(indexOf, book);
//        }
        Long id = book.getId();
        Book bookToUpdate = getBookFromList(id);
        int indexOf = bookList.indexOf(bookToUpdate);
        logger.info(String.format("Index: %d", indexOf));
        bookList.set(indexOf, book);
    }

    @Override
    public void delete(Book book) {
        if (get(book.getId()).isPresent()) {
            Book bookToDelete = getBookFromList(book.getId());
            int index = bookList.indexOf(bookToDelete);
            bookList.remove(index);
        }
    }

    public Book getBookFromList(Long id) {
        return bookList.stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Book does not exist!!");
                });
    }
}
