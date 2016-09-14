package ru.shevtsov.servlets;

import ru.shevtsov.model.Book;
import ru.shevtsov.store.BookCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dead_rabbit on 14.09.2016.
 */
public class BookCreateServlet extends HttpServlet {
    final AtomicInteger ids = new AtomicInteger();

    private final BookCache BOOK_CACHE = BookCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        this.BOOK_CACHE.add(new Book(this.ids.incrementAndGet(), req.getParameter("name"), req.getParameter("author"), req.getParameter("description")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/view"));
    }

    @Override
    public void destroy() {
        super.destroy();
        BOOK_CACHE.close();
    }


}
