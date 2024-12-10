package rjcontactsapp.servlets.customsession.attributes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/customsession/attributes/login")
public class LoginServlet extends HttpServlet {

	static String path = "/customsession/attributes";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple authentication (replace with your own logic)
        if ("admin".equals(username) && "password".equals(password)) {
            // Generate a session token
            String sessionToken = UUID.randomUUID().toString();

         // Store session data in HttpSession
            HttpSession session = request.getSession();
            session.setAttribute("auth_token", sessionToken);  // Store token
            session.setAttribute("username", username);  // Store username

            // Create a cookie with the session token
            Cookie sessionCookie = new Cookie("CustomSessionId", sessionToken);
            sessionCookie.setHttpOnly(true);
            sessionCookie.setMaxAge(3600); // Set expiry time (1 hour)

            response.addCookie(sessionCookie);
            String protectedUrl = request.getContextPath() + path + "/protected";
            response.getWriter().write("<html><body>Login successful! <a href='" + protectedUrl + "'>Go to protected resource</a></body></html>");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String loginUrl = request.getContextPath() + path + "/login.html";
            response.setContentType("text/html");
            response.getWriter().write("<html><body>Invalid credentials! <a href='" + loginUrl + "'>Try again</a></body></html>");
        }
    }
        
    
}