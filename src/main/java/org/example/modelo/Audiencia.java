package org.example.modelo;

import org.bson.Document;

public class Audiencia {
    private String fechaEmision;
    private int numEspectadores;

    public Audiencia(String fechaEmision, int numEspectadores) {
        this.fechaEmision = fechaEmision;
        this.numEspectadores = numEspectadores;
    }

    public String getFechaEmision() {return fechaEmision;}
    public void setFechaEmision(String fechaEmision) {this.fechaEmision = fechaEmision;}

    public int getNumEspectadores() {return numEspectadores;}
    public void setNumEspectadores(int numEspectadores) {this.numEspectadores = numEspectadores;}

    public Document toDocument(){
        return new Document("fecha", fechaEmision)
                .append("espectadores", numEspectadores);
    }
    public static Audiencia fromDocument(Document document){
        String fechaEmision = document.getString("fecha");
        int numEspectadores = document.getInteger("espectadores");
        return new Audiencia(fechaEmision, numEspectadores);
    }

    @Override
    public String toString() {
        return "Audiencia{" +
                "fechaEmision='" + fechaEmision + '\'' +
                ", numEspectadores=" + numEspectadores +
                '}';
    }
}
