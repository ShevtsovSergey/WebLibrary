package ru.shevtsov.store;

import ru.shevtsov.model.Book;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dead_rabbit on 14.09.2016.
 */
public interface Storage {
     Collection<Book> values();
     int add(final Book book);
     void edit(final Book book);
     void delete(final int id);
     Book get(final int id);
     ArrayList<Book> findByName(final String name);
     int generateId();
     void close();
}
