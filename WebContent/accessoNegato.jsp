<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accesso Negato</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }
        .container {
            display: inline-block;
            text-align: left;
        }
        .button {
            display: inline-block;
            font-size: 16px;
            padding: 10px 20px;
            margin-top: 20px;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
            text-decoration: none;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Accesso Negato</h1>
        <p>Non hai i permessi per accedere a questa pagina.</p>
        <a href="login.jsp" class="button">Torna al Login</a>
    </div>
</body>
</html>