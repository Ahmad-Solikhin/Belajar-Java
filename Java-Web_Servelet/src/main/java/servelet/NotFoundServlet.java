package servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/not-found")
public class NotFoundServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = """
                {
                    "message" : "not found"
                }
                """;

        resp.setHeader("Content-Type", "application/json");
        resp.setStatus(404);
        resp.getWriter().println(json);
    }
}
