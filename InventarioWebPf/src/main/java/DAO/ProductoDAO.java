/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daff
 */
public class ProductoDAO {
    
     private Connection conn;

    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Producto> listarProductos() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt("id_producto"));
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setCategoria(rs.getString("categoria"));
                p.setMarca(rs.getString("marca"));
                p.setProveedor(rs.getString("proveedor"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setPrecio_compra(rs.getDouble("precio_compra"));
                p.setPrecio_venta(rs.getDouble("precio_venta"));
                p.setFecha_registro(rs.getTimestamp("fecha_registro"));
                p.setEstado(rs.getString("estado"));
                lista.add(p);
            }
        }
        return lista;
    }

     public void insertarProducto(Producto p) throws SQLException {
    String sql = "INSERT INTO productos (codigo, nombre, descripcion, categoria, marca, proveedor, cantidad, precio_compra, precio_venta, fecha_registro, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, p.getCodigo());
        stmt.setString(2, p.getNombre());
        stmt.setString(3, p.getDescripcion());
        stmt.setString(4, p.getCategoria());
        stmt.setString(5, p.getMarca());
        stmt.setString(6, p.getProveedor());
        stmt.setInt(7, p.getCantidad());
        stmt.setDouble(8, p.getPrecio_compra());
        stmt.setDouble(9, p.getPrecio_venta());
        stmt.setTimestamp(10, p.getFecha_registro());
        stmt.setString(11, p.getEstado());
        stmt.executeUpdate();
    }
}
     
     public Producto obtenerPorId(int id) throws SQLException {
    String sql = "SELECT * FROM productos WHERE id_producto = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Producto p = new Producto();
            p.setId_producto(rs.getInt("id_producto"));
            p.setCodigo(rs.getString("codigo"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setCategoria(rs.getString("categoria"));
            p.setMarca(rs.getString("marca"));
            p.setProveedor(rs.getString("proveedor"));
            p.setCantidad(rs.getInt("cantidad"));
            p.setPrecio_compra(rs.getDouble("precio_compra"));
            p.setPrecio_venta(rs.getDouble("precio_venta"));
            p.setFecha_registro(rs.getTimestamp("fecha_registro"));
            p.setEstado(rs.getString("estado"));
            return p;
        }
    }
    return null;
}

public void actualizarProducto(Producto p) throws SQLException {
    String sql = "UPDATE productos SET codigo=?, nombre=?, descripcion=?, categoria=?, marca=?, proveedor=?, cantidad=?, precio_compra=?, precio_venta=?, estado=? WHERE id_producto=?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, p.getCodigo());
        stmt.setString(2, p.getNombre());
        stmt.setString(3, p.getDescripcion());
        stmt.setString(4, p.getCategoria());
        stmt.setString(5, p.getMarca());
        stmt.setString(6, p.getProveedor());
        stmt.setInt(7, p.getCantidad());
        stmt.setDouble(8, p.getPrecio_compra());
        stmt.setDouble(9, p.getPrecio_venta());
        stmt.setString(10, p.getEstado());
        stmt.setInt(11, p.getId_producto());
        stmt.executeUpdate();
    }
}

public void eliminarProducto(int id) throws SQLException {
    String sql = "DELETE FROM productos WHERE id_producto = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}

public List<Producto> buscarProductos(String nombre, String categoria) throws SQLException {
    List<Producto> lista = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM productos WHERE 1=1");

    if (nombre != null && !nombre.isEmpty()) {
        sql.append(" AND nombre LIKE ?");
    }
    if (categoria != null && !categoria.isEmpty()) {
        sql.append(" AND categoria LIKE ?");
    }
    

    try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
        int i = 1;
        if (nombre != null && !nombre.isEmpty()) stmt.setString(i++, "%" + nombre + "%");
        if (categoria != null && !categoria.isEmpty()) stmt.setString(i++, "%" + categoria + "%");
       

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
    Producto p = new Producto();
    p.setId_producto(rs.getInt("id_producto"));
    p.setCodigo(rs.getString("codigo"));
    p.setNombre(rs.getString("nombre"));
    p.setDescripcion(rs.getString("descripcion"));
    p.setCategoria(rs.getString("categoria"));
    p.setMarca(rs.getString("marca"));
    p.setProveedor(rs.getString("proveedor"));
    p.setCantidad(rs.getInt("cantidad"));
    p.setPrecio_compra(rs.getDouble("precio_compra"));
    p.setPrecio_venta(rs.getDouble("precio_venta"));
    p.setFecha_registro(rs.getTimestamp("fecha_registro"));
    p.setEstado(rs.getString("estado"));
    lista.add(p);
}
    }
    return lista;
}

}
    

