package rjcontactsapp.servlets.dashboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if the user is logged in
        if (session != null && session.getAttribute("username") != null) {
        	System.out.println("session id "+ session.getId());
        	System.out.println("user name "+ session.getAttribute("username"));
        	request.getRequestDispatcher("dashboard/dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+LoginServlet.dashboardpath+"/login.jsp?error=Please login first"));
        }
    }
}

