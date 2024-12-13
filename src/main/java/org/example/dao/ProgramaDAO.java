package org.example.dao;

import org.example.modelo.Programa;

import java.util.List;

public interface ProgramaDAO {
    void insertar(Programa programa);
    Programa findById(String id);
    List<Programa> findAll();
    void actualizar(Programa programa);
    void eliminar(String id);
    List<Programa> findByCategoria(String categoria);
    Programa programaConMayorAudiencia(String fecha);
    double calcularAudienciaMedia(String id, String fechaInicio, String fechaFin);

}
