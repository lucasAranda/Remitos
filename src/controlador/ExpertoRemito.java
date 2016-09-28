/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import database.FachadaRemito;
import dto.DtoRemito;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maquina0
 */
public class ExpertoRemito {

    private FachadaRemito fachadaRemito;
    private ExpertoCrearExcel expertoCrearExcel;

    public ExpertoRemito() {
        this.fachadaRemito = new FachadaRemito();
        this.expertoCrearExcel = new ExpertoCrearExcel();
    }

    public List<String> buscarBasesDatos() {
        return fachadaRemito.buscarBasesDatos();
    }

    public List<String> obtenerClientes(String empresa) {
        List<String> clientes = new ArrayList<>();
        if (fachadaRemito.seleccionarEmpresa(empresa)) {
            clientes = fachadaRemito.obtenerClientes();
        }
        System.out.println("Cantidad: " + clientes.size());
        return clientes;
    }

    public List<DtoRemito> obtenerRemitosCliente(String cliente, String fechaDesde, String fechaHasta) {
        return fachadaRemito.buscarRemitosCliente(cliente, fechaDesde, fechaHasta);
    }

    public List<DtoRemito> obtenerRemitos(String fechaDesde, String fechaHasta) {
        return fachadaRemito.buscarRemitos(fechaDesde, fechaHasta);
    }

    public void guardarExcel(List<DtoRemito> remitos, String desde, String hasta) throws IOException {
       expertoCrearExcel.crearExcel(remitos, desde, hasta);
    }
}
