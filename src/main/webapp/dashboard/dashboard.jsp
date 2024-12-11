<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome to the Dashboard</h1>
    <p>Hello, <%= request.getSession().getAttribute("username") %>!</p>
    <a href="dashboard/logout">Logout</a>
</body>
</html>
