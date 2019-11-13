package app.servlets;

import app.SpringConfiguration;
import app.service.WordService;
import entity.Word;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class NewServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initRequestDispatcher(req,resp,"views/new.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Word word = new Word(
                new String(req.getParameter("russian").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                req.getParameter("spanish"),
                req.getParameter("english"),
                req.getParameter("french"));
        wordService.addNewWord(word);
        doGet(req, resp);
    }
}
