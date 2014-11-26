/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.dagss.ejemplojsf.daos;

import es.uvigo.dagss.ejemplojsf.entidades.Libro;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ribadas
 */
@Stateless
@LocalBean
public class LibroDAO {
    @PersistenceContext
    EntityManager em;
    
    public void crear(Libro libro) {
        em.persist(libro);
    }
    
    public void actualizar(Libro libro) {
        em.merge(libro);
    }
    
    public void borrar(Libro libro) {
        em.remove(libro);
    }
    
    public Libro buscar(Long id) {
        return em.find(Libro.class, id);
    }
    
    public List<Libro> buscarTodos() {
        Query q = em.createQuery("SELECT l FROM Libro l");
        return q.getResultList();
    }
}
