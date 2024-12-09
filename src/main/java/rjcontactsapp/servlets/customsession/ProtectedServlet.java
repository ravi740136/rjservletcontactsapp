package rjcontactsapp.servlets.customsession;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/customsession/protected")
public class ProtectedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("CustomSessionId".equals(cookie.getName())) {
                    String sessionToken = cookie.getValue();
                    System.out.println("session token "+sessionToken);
                    System.out.println("token key set "+LoginServlet.sessionStore.keySet());
                    // Validate the session token
                    if (LoginServlet.sessionStore.containsKey(sessionToken)) {
                        String username = LoginServlet.sessionStore.get(sessionToken);
                        String logoutUrl = request.getContextPath() + "/customsession/logout";
                        response.getWriter().write("<html><body>Hello, " + username + "! You are authenticated. <a href='" + logoutUrl + "'>Logout</a></body></html>");
                        return;
                    }
                }
            }
        }

     // Unauthorized access
        String loginUrl = request.getContextPath() + "/customsession/login.html";
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/html");
        response.getWriter().write("<html><body>Unauthorized access! <a href='" + loginUrl + "'>Login here</a></body></html>");
    }
}
