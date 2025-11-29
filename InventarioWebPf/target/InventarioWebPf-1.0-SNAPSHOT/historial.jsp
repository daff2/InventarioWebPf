<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.HistorialMovimiento"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Historial de Movimientos</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f7f7f7; margin: 20px; }
        h1 { text-align: center; color: #333; }
        table { width: 80%; margin: 0 auto; border-collapse: collapse; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        form { margin-top: 30px; text-align: center; }
        input[type="text"], input[type="number"] { padding: 5px; }
        button { padding: 6px 10px; background-color: #007bff; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>
    <h1>Historial de Movimientos</h1>
    
    <table>
        <tr>
            <th>ID Historial</th>
            <th>ID Movimiento</th>
            <th>Acción</th>
            <th>Fecha</th>
            <th>Usuario Responsable</th>
        </tr>
        <%
            List<HistorialMovimiento> lista = (List<HistorialMovimiento>) request.getAttribute("listaHistorial");
            if (lista != null && !lista.isEmpty()) {
                for (HistorialMovimiento hm : lista) {
        %>
        <tr>
            <td><%= hm.getIdHistorial() %></td>
            <td><%= hm.getIdMovimiento() %></td>
            <td><%= hm.getAccion() %></td>
            <td><%= hm.getFechaccion() %></td>
            <td><%= hm.getUsuarioResponsable() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="5">No hay registros en el historial</td></tr>
        <%
            }
        %>
    </table>

    <form action="HistorialMovimientoServlet" method="post">
        <h3>Registrar nuevo movimiento</h3>
        ID Movimiento: <input type="number" name="idMovimiento" required><br><br>
        Acción: <input type="text" name="accion" required><br><br>
        Usuario Responsable: <input type="number" name="usuarioResponsable" required><br><br>
        <button type="submit">Guardar</button>
    </form>

</body>
</html>