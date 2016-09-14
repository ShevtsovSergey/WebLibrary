package ru.shevtsov.servlets;

import ru.shevtsov.model.Book;
import ru.shevtsov.store.BookCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dead_rabbit on 14.09.2016.
 */
public class BookEditServlet extends HttpServlet {

    final AtomicInteger ids = new AtomicInteger();

    private static final BookCache BOOK_CACHE = BookCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("book", this.BOOK_CACHE.get(Integer.valueOf(req.getParameter("id"))));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/EditBook.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        this.BOOK_CACHE.edit(new Book(Integer.valueOf(req.getParameter("id")), req.getParameter("name"), req.getParameter("author"), req.getParameter("description")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/view"));
    }
    @Override
    public void destroy() {
        super.destroy();
        BOOK_CACHE.close();
    }

}
