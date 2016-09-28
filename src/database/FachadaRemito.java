/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import dto.DtoRemito;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maquina0
 */
public class FachadaRemito {

    private ConexionSQLServer conexion;

    public FachadaRemito() {
        conexion = ConexionSQLServer.getConexion();
    }

    public List<String> buscarBasesDatos() {
        List<String> basesDatos = new ArrayList<>();
        try {
            ResultSet rs = conexion.obtenerBases();
            while (rs.next()) {
                if (rs.getString(1).startsWith("ADIMIX")||rs.getString(1).startsWith("HORMICON")
                        ||rs.getString(1).startsWith("COMPA")||rs.getString(1).startsWith("DESA")||rs.getString(1).startsWith("FID")) {
                    basesDatos.add(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FachadaRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return basesDatos;
    }

    public boolean seleccionarEmpresa(String empresa) {
        return conexion.modificarConexion(empresa);
    }

    public List<String> obtenerClientes() {
        List<String> clientes = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps = conexion.conn.prepareStatement("SELECT RAZON_SOCI FROM GVA14 WHERE RAZON_SOCI LIKE '%+%' OR RAZON_SOCI LIKE '%*%'");
            rs = ps.executeQuery();
            while(rs.next()){
                clientes.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FachadaRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    public List<DtoRemito> buscarRemitosCliente(String cliente, String fechaDesde, String fechaHasta) {
        List<DtoRemito> remitos = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps = conexion.conn.prepareStatement("SELECT GVA14.RAZON_SOCI, STA14.N_REMITO, STA14.FECHA_MOV, STA11.DESCRIPCIO, STA20.CANTIDAD, STA20.CANT_PEND " +
                    "FROM GVA14 " +
                    "INNER JOIN STA14 ON GVA14.COD_CLIENT = STA14.COD_PRO_CL " +
                    "INNER JOIN STA20 ON STA14.NCOMP_IN_S = STA20.NCOMP_IN_S " +
                    "INNER JOIN STA11 ON STA20.COD_ARTICU = STA11.COD_ARTICU " +
                    "WHERE GVA14.RAZON_SOCI = ? AND STA14.FECHA_MOV BETWEEN ? AND ?");
            ps.setString(1, cliente);
            ps.setString(2, fechaDesde);
            ps.setString(3, fechaHasta);
            rs = ps.executeQuery();
            while (rs.next()) {
                DtoRemito remito = new DtoRemito();
                remito.setCliente(rs.getString(1));
                remito.setNroRemito(rs.getString(2));
                remito.setFecha(rs.getString(3));
                remito.setArticulo(rs.getString(4));
                remito.setCantidad(rs.getDouble(5));
                remito.setCantidadPendiente(rs.getDouble(6));
                remitos.add(remito);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FachadaRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return remitos;
    }

    public List<DtoRemito> buscarRemitos(String fechaDesde, String fechaHasta) {
        List<DtoRemito> remitos = new ArrayList<>();
        try {
            ResultSet rs;
            PreparedStatement ps = conexion.conn.prepareStatement("SELECT GVA14.RAZON_SOCI, STA14.N_REMITO, STA14.FECHA_MOV, STA11.DESCRIPCIO, STA20.CANTIDAD, STA20.CANT_PEND " +
                    "FROM GVA14 " +
                    "INNER JOIN STA14 ON GVA14.COD_CLIENT = STA14.COD_PRO_CL " +
                    "INNER JOIN STA20 ON STA14.NCOMP_IN_S = STA20.NCOMP_IN_S " +
                    "INNER JOIN STA11 ON STA20.COD_ARTICU = STA11.COD_ARTICU " +
                    "WHERE (GVA14.RAZON_SOCI LIKE '%+%' OR GVA14.RAZON_SOCI LIKE '%*%') " +
                    "AND STA14.FECHA_MOV BETWEEN ? AND ?");
            ps.setString(1, fechaDesde);
            ps.setString(2, fechaHasta);
            rs = ps.executeQuery();
            while (rs.next()) {
                DtoRemito remito = new DtoRemito();
                remito.setCliente(rs.getString(1));
                remito.setNroRemito(rs.getString(2));
                remito.setFecha(rs.getString(3));
                remito.setArticulo(rs.getString(4));
                remito.setCantidad(rs.getDouble(5));
                remito.setCantidadPendiente(rs.getDouble(6));
                remitos.add(remito);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FachadaRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return remitos;
    }
}