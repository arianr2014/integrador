<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>JSP Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/sitio.css">
    <style>

        
        .login-container {
            width: 300px;
            margin: 100px auto;
            padding: 20px;
            background-color: #deeafc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }
        
        .login-container h1 {
            margin-bottom: 40px;
            text-align: center;
        }
        
        form {
            display: flex;
            flex-direction: column;
        }
        
        label {
            margin-bottom: 30px;
        }
        

        

    </style>
    <script>
        function showErrorPopup(message) {
            var popup = document.getElementById("error-popup");
            var popupMessage = document.getElementById("error-popup-message");
            popupMessage.innerText = message;
            popup.style.display = "block";
        }

        function closeErrorPopup() {
            var popup = document.getElementById("error-popup");
            popup.style.display = "none";
        }

        window.onload = function() {
            var errorMessage = '<%= request.getAttribute("errorMessage") %>';
            if (errorMessage && errorMessage !== "null") {
                showErrorPopup(errorMessage);
            }
        }
    </script>
</head>

<body class="header-background">
    <div class="login-container">
        <h1>Inicio de Sesion</h1>
        <form action="ValidarLogin" method="post">
            <label>Usuario</label>
            <input type="text" name="txtUsuario" placeholder="Usuario">
            <label>Contraseña</label>
            <input type="password" name="txtClave" placeholder="Clave">
            <input type="submit" name="validar" value="Aceptar"> 
        </form>
    </div>

    <!-- Cuadro flotante de error -->
    <div id="error-popup" class="error-popup">
        <span id="error-popup-message"></span>
        <button onclick="closeErrorPopup()">Cerrar</button>
    </div>
</body>
</html>
