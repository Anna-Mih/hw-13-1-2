package repository;

import domain.AlreadyExistException;
import domain.NotFoundException;
import domain.Product;

public class Repository {
    private Product[] items = new Product[0];

    public void save(Product item) {
        int id = item.getId();
        if (findById(id)) {
            throw new AlreadyExistException("Товар с id " + id + " уже существует");

        }
        int length = items.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public Product[] findAll() {
        return items;
    }

    public void removeById(int id) {
        if (!findById(id)) {
            throw new NotFoundException("Введен несуществующий ID " + id);
        }
        int length = items.length - 1;
        Product[] tmp = new Product[length];
        int index = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index = index + 1;
            }
        }
        items = tmp;
    }

    public boolean findById(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
