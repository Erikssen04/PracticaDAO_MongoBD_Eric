package org.example.modelo;

import org.bson.Document;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Programa {
    private String id;
    private String nombre;
    private String categoria;
    private List<Horario> horario;
    private List<Audiencia> audiencia;
    private List<Colaborador> colaboradores;

    public Programa(String id, String nombre, String categoria,
                    List<Horario> horario,
                    List<Audiencia> audiencia,
                    List<Colaborador> colaboradores) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.horario = horario;
        this.audiencia = audiencia;
        this.colaboradores = colaboradores;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria) {this.categoria = categoria;}

    public List<Horario> getHorario() {return horario;}
    public void setHorario(List<Horario> horario) {this.horario = horario;}

    public List<Audiencia> getAudiencia() {return audiencia;}
    public void setAudiencia(List<Audiencia> audiencia) {this.audiencia = audiencia;}

    public List<Colaborador> getColaboradores() {return colaboradores;}
    public void setColaboradores(List<Colaborador> colaboradores) {this.colaboradores = colaboradores;}

    public Document toDocument() {
        return new Document("_id", id)
                .append("nombre", nombre)
                .append("categoria", categoria)
                .append("horario", horario.stream()
                        .map(Horario::toDocument)
                        .toList())
                .append("audiencia", audiencia.stream()
                        .map(Audiencia::toDocument)
                        .toList())
                .append("colaboradores", colaboradores.stream()
                        .map(Colaborador::toDocument)
                        .toList());
    }

    public static Programa fromDocument(Document document){
        String id = document.getString("_id");
        String nombre = document.getString("nombre");
        String categoria = document.getString("categoria");

        List<Horario> horarios = ((List<Document>) document.get("horario")).stream()
                .map(Horario::fromDocument)
                .toList();

        List<Audiencia> audiencia = ((List<Document>) document.get("audiencia")).stream()
                .map(Audiencia::fromDocument)
                .toList();

        List<Colaborador> colaboradores = ((List<Document>) document.get("colaboradores")).stream()
                .map(Colaborador::fromDocument)
                .toList();

        return new Programa(id, nombre, categoria, horarios, audiencia, colaboradores);
    }

    @Override
    public String toString() {
        return "Programa{" +
                "_id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", horario=" + horario +
                ", audiencia=" + audiencia +
                ", colaboradores=" + colaboradores +
                '}';
    }
}
