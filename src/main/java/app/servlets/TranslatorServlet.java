package app.servlets;

import app.SpringConfiguration;
import app.service.WordService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TranslatorServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initRequestDispatcher(req,resp,"views/translator.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String word = wordService.translateWord(
                req.getParameter("first"),
                req.getParameter("second"),
                req.getParameter("wordToTranslate")
        );

        if(word==null){
            req.setAttribute("word", "can't find");
            logger.info("client can't find word");
            doGet(req,resp);
        }
        req.setAttribute("word", word);
        doGet(req, resp);
    }
}
