package app.servlets;

import app.dao.WordsDao;
import entity.Word;

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
        req.getParameter("first");
        System.out.println(req.getParameter("first"));
        String secondLanguage = req.getParameter("second");
        System.out.println(req.getParameter("second"));
        req.getParameter("wordToTranslate");

        WordsDao wordsDao = new WordsDao();
        Word wordFromDb = null;
        try {
            wordFromDb = wordsDao.getWordByLanguage(req.getParameter("wordToTranslate"), req.getParameter("first"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(wordFromDb==null){
            req.setAttribute("word", "can't find");
            doGet(req,resp);
        }


        switch (secondLanguage){
            case "russian" :
                req.setAttribute("word", wordFromDb.getRussian());
                break;
            case "english" :
                req.setAttribute("word", wordFromDb.getEnglish());
                break;

            case "spanish" :
                req.setAttribute("word", wordFromDb.getSpanish());
                break;

            case "french" :
                req.setAttribute("word", wordFromDb.getFrench());
                break;
        }

        doGet(req, resp);

    }
}
