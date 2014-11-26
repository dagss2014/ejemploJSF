/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.dagss.ejemplojsf.controladores;

import es.uvigo.dagss.ejemplojsf.daos.LibroDAO;
import es.uvigo.dagss.ejemplojsf.entidades.Libro;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ribadas
 */
@Named(value = "libroControlador")
@SessionScoped
public class LibroControlador implements Serializable {
    List<Libro> libros;
    Libro libroActual;
    boolean esNuevo;
    
    @EJB
    LibroDAO libroDAO;

    
    @PostConstruct
    public void inicializar() {
        libros = libroDAO.buscarTodos();
    }
    
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Libro getLibroActual() {
        return libroActual;
    }

    public void setLibroActual(Libro libroActual) {
        this.libroActual = libroActual;
    }
    
    public String doEliminar(Libro libroSeleccionado) {        
        libroDAO.borrar(libroSeleccionado);
        libros = libroDAO.buscarTodos();
        return "libros";
    }
    
    public String doNuevo(){
        libroActual = new Libro();
        esNuevo = true;
        return "editarLibro";
    }
    
    public String doModificar(Libro libroSeleccionado) {
        libroActual = libroSeleccionado;
        esNuevo = false;
        return "editarLibro";    
    }
    
    public String doGuardar() {
        if (esNuevo) {
            libroDAO.crear(libroActual);
        }
        else {
            libroDAO.actualizar(libroActual);
        }
        libros = libroDAO.buscarTodos();
        return "libros";
    }
    
    public String doCancelar(){
        return "libros";
    }
}
