package ru.shevtsov.store;

import ru.shevtsov.model.Book;
import ru.shevtsov.service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dead_rabbit on 14.09.2016.
 */
public class JdbcStorage implements Storage {
    private Connection connection;

    public JdbcStorage() {
        final Settings settings = Settings.getInstance();
        try {
            Class.forName(settings.value("jdbc.driver_class"));
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Book> values() {
        final List<Book> books = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM \"public\".\"books\"")) {
            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int add(Book book) {
        try (final PreparedStatement statement = this.connection.prepareStatement("INSERT INTO \"public\".\"books\" (name, author, description) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getDescription());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new book");
    }

    @Override
    public void edit(Book book) {
            try (final PreparedStatement statement = this.connection.prepareStatement("UPDATE \"public\".\"books\" SET name=?, author=?, description=? WHERE id=" + book.getId())) {
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            try (final PreparedStatement statement = this.connection.prepareStatement("DELETE FROM \"public\".\"books\" WHERE id=" + id)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
    public Book get(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM \"public\".\"books\" WHERE id=(?)")) {
            statement.setInt(1, id);
            try(final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Book %s does not exists", id));
    }

    @Override
    public ArrayList<Book> findByName(String name) {
        ArrayList<Book> findBooks = new ArrayList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM \"public\".\"books\" WHERE name=(?)")) {
            statement.setString(1, name);
            try(final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    findBooks.add(new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("description")));
                }
            }
            return findBooks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("Book %s does not exists", name));
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
