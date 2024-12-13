package org.example.modelo;

import org.bson.Document;

public class Horario {
    private String dia;
    private String hora;

    public Horario(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public String getDia() {return dia;}
    public void setDia(String dia) {this.dia = dia;}

    public String getHora() {return hora;}
    public void setHora(String hora) {this.hora = hora;}

    public Document toDocument(){
        return new Document("dia", dia)
                .append("hora", hora);
    }

    public static Horario fromDocument(Document document){
        String dia = document.getString("dia");
        String hora = document.getString("hora");
        return new Horario(dia, hora);
    }

    @Override
    public String toString() {
        return "Horario{" +
                "dia='" + dia + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
