package servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/request")
public class RequestServlet  extends HttpServlet
{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Method : " + req.getMethod());
        resp.getWriter().println("query : " + req.getQueryString());
        resp.getWriter().println("uri : " + req.getRequestURL());
        resp.getWriter().println("servlet path : " + req.getServletPath());
    }
}
