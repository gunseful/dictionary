package app.servlets;

import app.dao.WordsDao;
import entity.Word;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class NewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/new.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Word word = new Word(
                new String(req.getParameter("russian").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                req.getParameter("spanish"),
                req.getParameter("english"),
                req.getParameter("french"));
        WordsDao wordsDao = new WordsDao();
        try {
            wordsDao.addNewWord(word);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doGet(req, resp);




    }
}
