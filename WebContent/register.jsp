<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <script type="text/javascript">
        // Function to show an alert based on the success parameter in the URL
        function showSuccessAlert() {
            var urlParams = new URLSearchParams(window.location.search);
            var success = urlParams.get('success');
            if (success === 'true') {
                alert("Registrazione avvenuta con successo. Puoi tornare alla pagina di login.");
            } else if (success === 'false') {
                alert("Errore durante la registrazione. Per favore riprova.");
            }
        }
        
        // Ensure the function runs once the page has loaded
        window.onload = showSuccessAlert;
    </script>
    <style>
        /* Basic styling for body and container */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
            text-align: center;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="date"],
        button {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        input::placeholder {
            color: #888;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Registration Form</h1>
        <form action="register" method="post">
            <input type="text" name="nome" placeholder="Nome" required>
            <input type="text" name="cognome" placeholder="Cognome" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="text" name="telefono" placeholder="Telefono" required>
            <input type="date" name="dataNascita" placeholder="Data di Nascita" required>
            <button type="submit">Registrati</button>
            <a href="login.jsp">Login</a>
        </form>
    </div>
</body>
</html>
