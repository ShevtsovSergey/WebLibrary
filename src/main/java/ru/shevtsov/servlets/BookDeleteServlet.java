package ru.shevtsov.servlets;

import ru.shevtsov.store.BookCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dead_rabbit on 14.09.2016.
 */
public class BookDeleteServlet extends HttpServlet {

    private static final BookCache BOOK_CACHE = BookCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BOOK_CACHE.delete(Integer.valueOf(req.getParameter("id")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/view"));
    }

    @Override
    public void destroy() {
        super.destroy();
        BOOK_CACHE.close();
    }

}
