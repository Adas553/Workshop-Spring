package pl.coderslab.pojoClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
    public Book get(Long id) throws IndexOutOfBoundsException {
        return bookList.get((int) (id - 1));
    }

    @Override
    public void add(Book book) {
        book.setId(nextId++);
        bookList.add(book);
    }

    @Override
    public void update(Book book) {
        Long id = book.getId();
        Book bookToUpdate = bookList.get((int) (id - 1));
        if (bookToUpdate != null) {
            int indexOf = bookList.indexOf(bookToUpdate);
            logger.info(String.format("Index: %d", indexOf));
            bookList.set(indexOf, book);
        }
    }

    @Override
    public void delete(Long id) throws IndexOutOfBoundsException{
        if (get(id) != null) {
            Book bookToDelete = bookList.get((int) (id - 1));
            int indexOf = bookList.indexOf(bookToDelete);
            bookList.remove(indexOf);
        }
    }
}
