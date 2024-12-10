package rjcontactsapp.servlets.customsession.attributes;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/customsession/attributes/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);  // Do not create a new session if it doesn't exist

        if (session != null) {
            // Invalidate the session to clear all session data
            session.invalidate();
        }
    	
    	Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("CustomSessionId".equals(cookie.getName())) {


                    // Invalidate the cookie
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
          
                    String loginUrl = request.getContextPath() + LoginServlet.path + "/login.html";                   
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