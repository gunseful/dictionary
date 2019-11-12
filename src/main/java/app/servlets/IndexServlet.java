package app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class IndexServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("allwords") != null) {
            request.setAttribute("all", wordService.getAllWords());
        }

        if (request.getParameter("Delete") != null) {
            wordService.delete(Integer.parseInt(request.getParameter("Delete")));
            request.setAttribute("all", wordService.getAllWords());
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initRequestDispatcher(req,resp,"index.jsp");
    }
}
