<%@ page import="java.util.List,  com.jwt.hibernate.bean.Veicolo" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista veicoli disponibili</title>
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
    <h1>Lista Veicoli Disponibili</h1>

    <table>
        <thead>
            <tr>
                <th>Marca</th>
                <th>Modello</th>
                <th>Anno</th>
                <th>Azioni</th>
            </tr>
        </thead>
        <tbody>
            <%
            // Recupera la lista di veicoli dalla request
            List<Veicolo> veicoli = (List<Veicolo>) request.getAttribute("VEICOLI_LIST");

            if (veicoli != null && !veicoli.isEmpty()) {
                boolean foundAvailable = false;

                for (Veicolo veicolo : veicoli) {
                    if ("Disponibile".equals(veicolo.getDisponibilita())) {
                        foundAvailable = true;
            %>
                        <tr>
                            <td><%= veicolo.getMarca() %></td>
                            <td><%= veicolo.getModello() %></td>
                            <td><%= veicolo.getAnno() %></td>
                            <td>
                               
                                <a href="CustomerServlet?command=LOAD&veicolo_id=<%= veicolo.getId() %>">Prenota</a>
                            </td>
                        </tr>
            <%
                    }
                }

                if (!foundAvailable) {
            %>
                    <tr>
                        <td colspan="4">Nessun veicolo disponibile</td>
                    </tr>
            <%
                }
            } else {
            %>
                <tr>
                    <td colspan="4">Nessun veicolo disponibile</td>
                </tr>
            <%
            }
            %>
        </tbody>
    </table>
</body>
</html>