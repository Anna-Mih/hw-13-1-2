package repository;

import domain.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class RepositoryTest {

    @Test
    void shouldRemoveById() {
        Repository repo = new Repository();
        Book book1 = new Book(1, "stihi", 200, "esenin");
        Smartphone smartphone1 = new Smartphone(2, "iphone11", 80000, "apple");

        repo.save(book1);
        repo.save(smartphone1);
        repo.removeById(2);

        Product[] expected = {book1};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFoundEception() {
        Repository repo = new Repository();
        Book book1 = new Book(1, "stihi", 200, "esenin");
        Smartphone smartphone1 = new Smartphone(2, "iphone11", 80000, "apple");

        repo.save(book1);
        repo.save(smartphone1);

        assertThrows(NotFoundException.class, () -> repo.removeById(5));
    }

    @Test
    void shouldSave() {
        Repository repo = new Repository();
        Book book1 = new Book(1, "stihi", 200, "esenin");
        Smartphone smartphone1 = new Smartphone(2, "iphone11", 80000, "apple");

        repo.save(book1);
        repo.save(smartphone1);

        Product[] expected = {
                new Book(1, "stihi", 200, "esenin"),
                new Smartphone(2, "iphone11", 80000, "apple")
        };
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void alreadyExistException() {
        Repository repo = new Repository();
        Book book1 = new Book(1, "java", 500, "petrov");
        Book book2 = new Book(2, "html/css", 400, "ivanov");
        Smartphone smartphone = new Smartphone(1, "nokia", 5000, "nokia co");

        repo.save(book1);
        repo.save(book2);

        assertThrows(AlreadyExistException.class, () -> repo.save(smartphone));
    }
}