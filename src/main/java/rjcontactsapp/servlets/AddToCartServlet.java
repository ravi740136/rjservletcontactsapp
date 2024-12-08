package rjcontactsapp.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
  
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the product details from the request
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");

        // Get or create the session
        HttpSession session = request.getSession();

        // Retrieve the cart from the session (create it if it doesn't exist)
        Map<String, String> cart = (Map<String, String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        // Add the product to the cart
        cart.put(productId, productName);
        session.setAttribute("cart", cart);

        // Redirect to the cart page
        response.sendRedirect("viewCart");
    }
}
