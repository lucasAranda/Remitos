/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

/**
 *
 * @author maquina0
 */
public class DtoRemito {
    
    private String cliente;
    private String nroRemito;
    private String fecha;
    private String articulo;
    private double cantidad;
    private double cantidadPendiente;

    public DtoRemito() {
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(String nroRemito) {
        this.nroRemito = nroRemito;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(double cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }
}
