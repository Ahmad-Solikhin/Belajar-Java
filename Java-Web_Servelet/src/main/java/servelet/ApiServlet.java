package servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servelet.model.SayHelloRequest;
import servelet.util.JsonUtil;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/api/say-hello")
public class ApiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SayHelloRequest sayHelloRequest = JsonUtil.getObjectMapper().readValue(req.getReader(), SayHelloRequest.class);

        String sayHello = "Hello " + sayHelloRequest.getFirstName() + " " + sayHelloRequest.getLastName();

        Map<String, Object> response = Map.of(
                "data", sayHelloRequest,
                "greeting", sayHello
        );

        String json = JsonUtil.getObjectMapper().writeValueAsString(response);

        resp.setHeader("content-type", "application/json");
        resp.getWriter().println(json);
    }
}
