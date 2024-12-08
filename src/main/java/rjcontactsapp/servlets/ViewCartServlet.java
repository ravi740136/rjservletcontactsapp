package rjcontactsapp.servlets;


import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewCart")
public class ViewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            // No session exists, handle the scenario
            response.getWriter().println("No active session found.");
        } else {
        // Get the cart from the session
        Map<String, String> cart = (Map<String, String>) session.getAttribute("cart");

        // Forward the request to a JSP to display the cart
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("viewCart.jsp").forward(request, response);
        }
        }
}
