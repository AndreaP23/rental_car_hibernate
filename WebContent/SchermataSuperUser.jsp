<%@ page import="java.util.List, com.jwt.hibernate.bean.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista degli Utenti Registrati</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <a href="LogoutServlet">Logout</a>
    <h1>Lista degli Utenti Registrati</h1>

    <table>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <%
           
            List<User> users = (List<User>) request.getAttribute("USER_LIST");

            if (users != null && !users.isEmpty()) {
                for (User user : users) {
            %>
                    <tr>
                        <td><%= user.getNome() %></td>
                        <td><%= user.getCognome() %></td>
                        <td><%= user.getEmail() %></td>
                    </tr>
            <%
                }
            } else {
            %>
                <tr>
                    <td colspan="3">Nessun utente trovato</td>
                </tr>
            <%
            }
            %>
        </tbody>
    </table>
    
</body>
</html>
