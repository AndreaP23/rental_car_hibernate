<%@ page import="java.util.List, com.jwt.hibernate.bean.Veicolo" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dettaglio Veicolo</title>
    <script type="text/javascript">
        function showAlert(message) {
            alert(message);
        }
    </script>
</head>
<body>
    <a href="/rental_car/CustomerServlet">Torna alla lista</a>
    <h1>Dettagli Veicolo</h1>

    <%
    Veicolo veicolo = (Veicolo) request.getAttribute("VEICOLO_DETAIL");
    String bookingStatus = (String) request.getAttribute("bookingStatus");

    if (veicolo != null) {
    %>
        <p>Marca: <%= veicolo.getMarca() %></p>
        <p>Modello: <%= veicolo.getModello() %></p>
        <p>Anno: <%= veicolo.getAnno() %></p>
        <p>Disponibilità: <%= veicolo.getDisponibilita() %></p>

        <% if ("Disponibile".equalsIgnoreCase(veicolo.getDisponibilita())) { %>
        <h2>Prenota questo veicolo</h2>
        <form action="CustomerServlet" method="post">
            <input type="hidden" name="veicolo_id" value="<%= veicolo.getId() %>">
            <input type="hidden" name="command" value="BOOK">

            <label for="startDate">Data inizio:</label>
            <input type="date" id="startDate" name="startDate" required>
            <br>

            <label for="endDate">Data fine:</label>
            <input type="date" id="endDate" name="endDate" required>
            <br>

            <button type="submit">Prenota</button>
        </form>
        <% } else { %>
        <p>Il veicolo non è disponibile per la prenotazione.</p>
        <% } %>
        
    <%
    } else {
    %>
        <p>Veicolo non trovato.</p>
    <%
    }
    
    if (bookingStatus != null) {
    %>
    <script type="text/javascript">
        window.onload = function() {
            <% if ("success".equals(bookingStatus)) { %>
                showAlert("Prenotazione avvenuta con successo!");
            <% } else if ("error".equals(bookingStatus)) { %>
                showAlert("Errore durante la prenotazione. Riprova.");
            <% } %>
        };
    </script>
    <%
    }
    %>
</body>
</html>