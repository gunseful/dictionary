package app.servlets;

import app.dao.WordsDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("allwords") != null) {
            WordsDao wordsDao = new WordsDao();
            try {
                request.setAttribute("all", wordsDao.getAllWords());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("Delete") != null) {
            WordsDao wordsDao = new WordsDao();
            try {
                wordsDao.delete(Integer.parseInt(request.getParameter("Delete")));
                request.setAttribute("all", wordsDao.getAllWords());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        doGet(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);

    }
}
