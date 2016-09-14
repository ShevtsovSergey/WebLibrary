package ru.shevtsov.servlets;

import ru.shevtsov.store.BookCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dead_rabbit on 15.09.2016.
 */
public class BookSearchServlet extends HttpServlet{
    private static final BookCache BOOK_CACHE = BookCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("searchBooks", this.BOOK_CACHE.findByName(req.getParameter("search")));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/SearchBook.jsp");
        dispatcher.forward(req, resp);
    }
}
