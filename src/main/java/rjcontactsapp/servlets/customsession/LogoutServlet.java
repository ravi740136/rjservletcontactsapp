package rjcontactsapp.servlets.customsession;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/customsession/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("CustomSessionId".equals(cookie.getName())) {
                    String sessionToken = cookie.getValue();

                    // Remove the session from the session store
                    LoginServlet.sessionStore.remove(sessionToken);

                    // Invalidate the cookie
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
          
                    String loginUrl = request.getContextPath() + "/customsession/login.html";                   
                    response.setContentType("text/html");
                    response.getWriter().write("<html><body>Logged out successfully! <a href='" + loginUrl + "'>Login here</a></body></html>");
                    return;
                }
            }
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("No active session found!");
    }
}