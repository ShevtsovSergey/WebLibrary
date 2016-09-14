package ru.shevtsov.store;

import ru.shevtsov.model.Book;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dead_rabbit on 14.09.2016.
 */
public class BookCache implements Storage {

    private static final BookCache INSTANCE = new BookCache();

    private Storage storage = new JdbcStorage();

    public static BookCache getInstance() {
        return INSTANCE;
    }

    @Override
    public Collection<Book> values() {
        return storage.values();
    }

    @Override
    public int add(final Book book) {
        return this.storage.add(book);
    }

    @Override
    public void edit(final Book book) {
        this.storage.edit(book);
    }

    @Override
    public void delete(final int id) {
        this.storage.delete(id);
    }

    @Override
    public Book get(final int id) {
        return this.storage.get(id);
    }

    @Override
    public ArrayList<Book> findByName(final String name) {
        return this.storage.findByName(name);
    }

    @Override
    public int generateId() {
        return this.storage.generateId();
    }

    @Override
    public void close() {
        this.storage.close();
    }

}
