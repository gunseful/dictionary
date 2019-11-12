package app.servlets;

import app.dao.ConnectionPool;
import app.service.WordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractServlet extends HttpServlet {

    protected static Logger logger = LogManager.getLogger(ConnectionPool.class);
    protected WordService wordService = new WordService();
    protected void initRequestDispatcher(HttpServletRequest req, HttpServletResponse resp, String viewName) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
        requestDispatcher.forward(req, resp);
    }
}
