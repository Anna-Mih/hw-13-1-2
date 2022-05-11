package domain;

import org.junit.jupiter.api.Test;
import repository.Repository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    Repository repo = new Repository();

    @org.junit.jupiter.api.Test
    void shouldSearchByIfNotExistText() {
        repo.save(new Book(1, "stihi", 200, "barto"));
        repo.save(new Smartphone(2, "sumsung", 12000, "company"));
        repo.save(new Book(3, "animals", 150, "bianki"));
        String text = "pushkin";
        ProductManager manager = new ProductManager(repo);

        Product[] expected = {};
        Product[] actual = manager.searchBy(text);

        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void shouldSearchByIfExistTextInOneFirstItem() {
        repo.save(new Book(1, "stihi", 200, "barto"));
        repo.save(new Smartphone(2, "sumsung", 12000, "company"));
        repo.save(new Book(3, "animals", 150, "bianki"));
        String text = "stihi";
        ProductManager manager = new ProductManager(repo);

        Product[] expected = {new Book(1, "stihi", 200, "barto")};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void shouldSearchByIfExistTextInSecondItem() {
        repo.save(new Book(1, "stihi", 200, "barto"));
        repo.save(new Smartphone(2, "sumsung", 12000, "company"));
        repo.save(new Book(3, "animals", 150, "bianki"));
        String text = "sumsung";
        ProductManager manager = new ProductManager(repo);

        Product[] expected = {new Smartphone(2, "sumsung", 12000, "company")};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void shouldSearchByIfExistTextInLastItem() {
        repo.save(new Book(1, "stihi", 200, "barto"));
        repo.save(new Smartphone(2, "sumsung", 12000, "company"));
        repo.save(new Book(3, "animals", 150, "bianki"));
        String text = "animals";
        ProductManager manager = new ProductManager(repo);

        Product[] expected = {new Book(3, "animals", 150, "bianki")};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void shouldSearchByIfExistTextInMoreThenOneItem() {
        repo.save(new Book(1, "stihi", 200, "barto"));
        repo.save(new Smartphone(2, "sumsung", 12000, "company1"));
        repo.save(new Book(3, "animals", 150, "bianki"));
        repo.save(new Smartphone(4, "sumsung", 15000, "company2"));
        String text = "sumsung";
        ProductManager manager = new ProductManager(repo);

        Product[] expected = {
                new Smartphone(2, "sumsung", 12000, "company1"),
                new Smartphone(4, "sumsung", 15000, "company2")
        };
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void shouldSearchByIfExistTextPartially() {
        repo.save(new Book(1, "stihi", 200, "petrov"));
        repo.save(new Smartphone(2, "sumsung", 12000, "company"));
        repo.save(new Book(3, "animals", 150, "bianki"));
        repo.save(new Smartphone(4, "sumsung-galaxy", 15000, "company"));
        repo.save(new Book(5, "petrov and sokolov", 200, "ivanov"));
        String text = "sumsung";
        ProductManager manager = new ProductManager(repo);

        Product[] expected = {
                new Smartphone(2, "sumsung", 12000, "company"),
                new Smartphone(4, "sumsung-galaxy", 15000, "company")
        };
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldMatchesIfNotContainsSearch() {
        Product item = new Smartphone(2, "sumsung", 12000, "company1");

        repo.save(new Book(1, "stihi", 200, "barto"));
        repo.save(item);
        repo.save(new Book(3, "animals", 150, "bianki"));
        repo.save(new Smartphone(4, "sumsung", 15000, "company2"));
        ProductManager manager = new ProductManager(repo);
        String search = "skazki";
        boolean match = manager.matches(item, search);

        assertFalse(match);
    }

    @Test
    void shouldMatchesIfContainsSearch() {
        Product item = new Smartphone(2, "sumsung", 12000, "company1");

        repo.save(new Book(1, "stihi", 200, "barto"));
        repo.save(item);
        repo.save(new Book(3, "animals", 150, "bianki"));
        repo.save(new Smartphone(4, "sumsung", 15000, "company2"));
        ProductManager manager = new ProductManager(repo);
        String search = "sumsung";
        boolean match = manager.matches(item, search);

        assertFalse(!match);
    }
}