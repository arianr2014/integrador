<!-- header.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Header</title>
    <!-- Bootstrap CSS <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">-->

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

    <link rel="stylesheet" href="css/sitio.css">
    <style>
        .navbar {
            background-color: #2e588e;
        }
        .navbar-nav .nav-link {
            color: white;
        }
        .navbar-nav .nav-link:hover {
            background-color: #82b0d9;
            color: white;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light ">
    <a class="navbar-brand" href="#" style="color: #cccccc;">Sistema POS</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="ControlerCliente?Op=Listar">Clientes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ControlerProducto?op=Listar">Productos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ControlerPedido?Op=Listar">Pedidos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="CerrarSesion">Cerrar Sesión</a>
            </li>
        </ul>
    </div>
</nav>

