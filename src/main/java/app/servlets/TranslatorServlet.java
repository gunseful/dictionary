package app.servlets;

import app.service.WordService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TranslatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/translator.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WordService wordService = new WordService();
        String word = wordService.translateWord(
                req.getParameter("first"),
                req.getParameter("second"),
                req.getParameter("wordToTranslate")
        );


        if(word==null){
            req.setAttribute("word", "can't find");
            doGet(req,resp);
        }

        req.setAttribute("word", word);

        doGet(req, resp);

    }
}
