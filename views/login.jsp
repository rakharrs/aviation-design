<%
String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <% if(error!=null){ %>
        <span style="color: red;"><%=error%></span>
    <% } %>
    <ul>
        <li>jean - [espace] (client)</li>
        <li>Admin - 123 (admin)</li>
        <li>Emp - 456 (employer)</li>
    </ul>
    <form action="login" method="post">
        <input type="text" name="name" placeholder="nom"><br>
        <input type="password" name="password" placeholder="mot de passe"><br>
        <input type="submit" value="login">
    </form>
</body>
</html>