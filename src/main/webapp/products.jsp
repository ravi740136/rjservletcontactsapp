<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <h1>Available Products</h1>
    <ul>
        <li>
            <strong>Laptop</strong> - rs 50,000
            <form action="addToCart" method="post">
                <input type="hidden" name="productId" value="101">
                <input type="hidden" name="productName" value="Laptop">
                <button type="submit">Add to Cart</button>
            </form>
        </li>
        <li>
            <strong>Smartphone</strong> - rs 20,000
            <form action="addToCart" method="post">
                <input type="hidden" name="productId" value="102">
                <input type="hidden" name="productName" value="Smartphone">
                <button type="submit">Add to Cart</button>
            </form>
        </li>
        <li>
            <strong>Headphones</strong> - rs 2,000
            <form action="addToCart" method="post">
                <input type="hidden" name="productId" value="103">
                <input type="hidden" name="productName" value="Headphones">
                <button type="submit">Add to Cart</button>
            </form>
        </li>
    </ul>
</body>
</html>
