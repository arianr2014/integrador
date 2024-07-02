<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.io.*, java.util.*, java.sql.*, Entidades.productoDAO, Entidades.pedidoDAO, Entidades.producto, Entidades.pedido" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Nuevo Pedido</title>
    <link rel="stylesheet" href="css/sitio.css">
    <style>

        .container {
            margin: 20px auto;
            width: 80%;
            max-width: 1200px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

    </style>
    <script>
        function getPrecio() {
            var selectBox = document.getElementsByName("Producto")[0];
            var selectedOption = selectBox.options[selectBox.selectedIndex];
            var precioField = document.getElementById("precio");
            precioField.value = selectedOption.getAttribute("data-precio");
        }

        function agregarProducto() {
            var selectBox = document.getElementsByName("Producto")[0];
            var selectedOption = selectBox.options[selectBox.selectedIndex];
            var producto = selectedOption.text;
            var precio = selectedOption.getAttribute("data-precio");
            var cantidad = document.getElementsByName("Cantidad")[0].value;

            // Construir el objeto de datos para enviar al servidor
            var datos = {
                producto: producto,
                precio: precio,
                cantidad: cantidad
            };

            // Realizar la solicitud AJAX para enviar los datos al servidor
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "ControlerPedido", true);
            xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                    // Manejar la respuesta del servidor si es necesario
                    actualizarTabla(JSON.parse(xhr.responseText));
                }
            };
            xhr.send(JSON.stringify(datos));
        }

        function actualizarTabla(producto) {
            var tabla = document.getElementById("tablaProductos");
            var fila = tabla.insertRow();
            var celdaProducto = fila.insertCell(0);
            var celdaPrecio = fila.insertCell(1);
            var celdaCantidad = fila.insertCell(2);

            celdaProducto.textContent = producto.producto;
            celdaPrecio.textContent = producto.precio;
            celdaCantidad.textContent = producto.cantidad;
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp" />
    <div class="barra">
        <h1>Nuevo Pedido</h1>
        <h1>Administrador</h1>
    </div>
    <div class="container-fluid">
        <form id="formularioPedido" action="ControlerPedido" method="post">   
            <div class="d-flex">
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group">
                                <label>Datos del Cliente</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="hidden" name="codigocliente" class="form-control" placeholder="Código">
                                    <input type="text" name="DNI" class="form-control" placeholder="DNI" value="${c.getDNI()}">
                                    <input type="submit" name="accion" value="BuscarCliente" class="btn btn-outline-info">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" name="Nombres" class="form-control" placeholder="Nombres" readonly value="<%= request.getAttribute("Nombres") != null ? request.getAttribute("Nombres") : "" %>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Datos Producto</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="codigoProducto" class="form-control" placeholder="Código">
                                    <input type="submit"  name="accion" value="BuscarProducto" class="btn btn-outline-info">
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" name="nombreProducto" class="form-control" placeholder="Producto" value="<%= request.getAttribute("nombreProducto") != null ? request.getAttribute("nombreProducto") : "" %>">
                                </div>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="precio" class="form-control" placeholder="Precio" id="precio" value="<%= request.getAttribute("precio") != null ? request.getAttribute("precio") : "" %>">
                                </div>
                                <div class="col-sm-3">
                                    <input type="number" name="cant" placeholder="Cantidad" class="form-control">
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" name="stock" placeholder="Stock" class="form-control" value="<%= request.getAttribute("stock") != null ? request.getAttribute("stock") : "" %>">
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="accion" value="Agregar" class="btn btn-outline-info">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group d-flex">
                                <label>Numero De Pedido</label>
                                <input type="text" name="IdPedido" class="form-control">
                            </div>
                            <table id="tablaProductos" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Producto</th>
                                        <th>Precio</th>
                                        <th>Cantidad</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Aquí se añadirán las filas de productos con JavaScript -->
                                </tbody>
                            </table>
                            <br/>
                            <div class="card-footer">
                                <div>
                                    <input type="submit" name="accion" value="Generar venta" class="btn btn-success">
                                    <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </form>
    </div>
</body>
</html>
