package rjcontactsapp.servlets;



import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // Set response content type
        response.setContentType("text/html");

        // Generate HTML response
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Contact Submission</title></head>");
        out.println("<body>");
        out.println("<h1>Thank You, " + name + "!</h1>");
        out.println("<p>We have received your message:</p>");
        out.println("<blockquote>" + message + "</blockquote>");
        out.println("<p>We will get back to you at " + email + ".</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
