package rjcontactsapp.servlets.customsession.attributes;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/customsession/attributes/protected")
public class ProtectedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get all cookies from the request
        Cookie[] cookies = request.getCookies();

        String sessionToken = null;

        // Look for the session token in the cookies
        for (Cookie cookie : cookies) {
            if ("CustomSessionId".equals(cookie.getName())) {
                sessionToken = cookie.getValue();
                System.out.println("Session token: " + sessionToken);
                break;
            }
        }

        if (sessionToken != null) {
            // Retrieve the session and check if the session contains the token
            HttpSession session = request.getSession(false);  // Don't create a new session if it doesn't exist
            if (session != null) {
                // Retrieve the session token stored in the session
                String sessionTokenFromSession = (String) session.getAttribute("auth_token");

                if (sessionToken.equals(sessionTokenFromSession)) {
                    // Token is valid, retrieve the username from the session
                    String username = (String) session.getAttribute("username");

                    if (username != null) {
                        // User is authenticated, respond with a message
                        String logoutUrl = request.getContextPath() + LoginServlet.path + "/logout";
                        response.getWriter().write("<html><body>Hello, " + username + "! You are authenticated. <a href='" + logoutUrl + "'>Logout</a></body></html>");
                        return;
                    }
                }
            }
        }
     // Unauthorized access
        String loginUrl = request.getContextPath() + LoginServlet.path + "/login.html";
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/html");
        response.getWriter().write("<html><body>Unauthorized access! <a href='" + loginUrl + "'>Login here</a></body></html>");
    }
}
