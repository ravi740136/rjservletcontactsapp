package rjcontactsapp.servlets.customsession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/customsession/login")
public class LoginServlet extends HttpServlet {

    static final Map<String, String> sessionStore = new HashMap<>(); // Map to store session data

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple authentication (replace with your own logic)
        if ("admin".equals(username) && "password".equals(password)) {
            // Generate a session token
            String sessionToken = UUID.randomUUID().toString();

            // Store the session in the session store
            sessionStore.put(sessionToken, username);

            // Create a cookie with the session token
            Cookie sessionCookie = new Cookie("CustomSessionId", sessionToken);
            sessionCookie.setHttpOnly(true);
            sessionCookie.setMaxAge(3600); // Set expiry time (1 hour)

            response.addCookie(sessionCookie);
            String protectedUrl = request.getContextPath() + "/customsession/protected";
            response.getWriter().write("<html><body>Login successful! <a href='" + protectedUrl + "'>Go to protected resource</a></body></html>");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String loginUrl = request.getContextPath() + "/customsession/login.html";
            response.setContentType("text/html");
            response.getWriter().write("<html><body>Invalid credentials! <a href='" + loginUrl + "'>Try again</a></body></html>");
        }
    }
        
    
}