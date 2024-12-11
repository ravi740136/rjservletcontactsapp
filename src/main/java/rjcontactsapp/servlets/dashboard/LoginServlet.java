package rjcontactsapp.servlets.dashboard;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/dashboard/login")
public class LoginServlet extends HttpServlet {
	static String dashboardpath = "/dashboard";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "password".equals(password)) {
            // Create a session and store the user's login status
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + dashboardpath));
        } else {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + dashboardpath+"/login.jsp?error=Invalid Credentials"));
        }
    }
}

