<%@ page import="java.util.Map" %>

<%
    Map<String, String> cart = (Map<String, String>) request.getAttribute("cart");
%>
<h1>Your Cart</h1>
<%
    if (cart == null || cart.isEmpty()) {
%>
    <p>Your cart is empty.</p>
<%
    } else {
%>
    <ul>
        <%
            for (Map.Entry<String, String> entry : cart.entrySet()) {
        %>
            <li><b>Product:</b> <%= entry.getValue() %> (ID: <%= entry.getKey() %>)</li>
        <%
            }
        %>
    </ul>
<%
    }
%>
<a href="products.jsp">Continue Shopping</a>
