<%@ page import="java.util.List, com.jwt.hibernate.bean.User, com.jwt.hibernate.bean.Prenotazione" %>
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
    
    <br>
    <br>
    <h1>Lista Prenotazioni</h1>
<table>
<thead>
    <tr>
        <th>Id Utente</th>
        <th>Id Veicolo</th>
        <th>Data Prenotazione</th>
        <th>Data Inizio</th>
        <th>Data Fine</th>
    </tr>
</thead>
<tbody>
    <%
    List<Prenotazione> prenotazioni = (List<Prenotazione>) request.getAttribute("PRENOTAZIONI_LIST");

    if (prenotazioni != null && !prenotazioni.isEmpty()) {
        for (Prenotazione prenotazione : prenotazioni) {
    %>
            <tr>
                <td><%= prenotazione.getUser().getId() %></td>
                <td><%= prenotazione.getVeicolo().getId() %></td>
                <td><%= prenotazione.getDataPrenotazione() %></td>
                <td><%= prenotazione.getDataInizio() %></td>
                <td><%= prenotazione.getDataFine() %></td>
            </tr>
    <%
        }
    } else {
    %>
        <tr>
            <td colspan="5">Nessuna prenotazione trovata</td>
        </tr>
    <%
    }
    %>
</tbody>
</table>
    
</body>
</html>
