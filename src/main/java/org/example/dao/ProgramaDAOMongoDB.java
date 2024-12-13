package org.example.dao;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.modelo.Programa;
import org.example.utils.MongoDBConnection;

import java.util.ArrayList;
import java.util.List;

public class ProgramaDAOMongoDB implements ProgramaDAO {
    private final MongoCollection<Document> coleccion;

    public ProgramaDAOMongoDB() {
        // Inicializamos la conexión con MongoDB usando el getter de la clase MongoDBConnection
        this.coleccion = MongoDBConnection.getDatabase();
    }

    @Override
    public void insertar(Programa programa) {
        try {
            // Convertimos el programa a un documento y lo insertamos
            Document documentPrograma = programa.toDocument();
            coleccion.insertOne(documentPrograma);

        } catch (Exception e) {
            System.out.println("No se ha podido insertar el nuevo programa");
            e.printStackTrace();
        }
    }

    @Override
    public Programa findById(String id) {
        try {
            // Creamos un documento que filtrará el programa por su ID
            Document documentProgramaById = new Document("_id", id);
            // Buscamos el documento en la colección
            Document document = coleccion.find(documentProgramaById).first();
            // Convertimos el documento encontrado en un objeto Programa
            return Programa.fromDocument(document);

        } catch (Exception e) {
            throw new RuntimeException("No se ha podido encontrar el programa especificado por id");
        }
    }

    @Override
    public List<Programa> findAll() {
        try {
            List<Programa> programas = new ArrayList<>();
            // Se itera sobre todos los documentos de la colección
            for (Document document : coleccion.find()) {
                // Se convierten a objetos Programa
                programas.add(Programa.fromDocument(document));
            }
            return programas;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los programas");
        }
    }

    @Override
    public void actualizar(Programa programa) {
        try {
            // Creamos un documento que filtra el programa por su ID
            Document documentProgramaById = new Document("_id", programa.getId());
            // actualizamos los campos del programa
            coleccion.updateOne(documentProgramaById, new Document("$set", programa.toDocument()));

        } catch (Exception e) {
            System.out.println("No se ha podido actualizar el programa");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String id) {
        try {
            Document documentProgramaById = new Document("_id", id);
            // Eliminación del programa
            coleccion.deleteOne(documentProgramaById);

        } catch (Exception e) {
            System.out.println("No se ha podido eliminar el programa");
            e.printStackTrace();
        }
    }

    @Override
    public List<Programa> findByCategoria(String categoria) {
        try {
            // Obtenemos los programas que coincidan con el document que filtra por categoria
            Document documentProgramaByCategoria = new Document("categoria", categoria);
            List<Programa> programasPorCategoria = new ArrayList<>();
            for (Document document : coleccion.find(documentProgramaByCategoria)) {
                programasPorCategoria.add(Programa.fromDocument(document));
            }
            return programasPorCategoria;

        } catch (Exception e) {
            throw new RuntimeException("No se ha podido encontrar el programa especificado por categoría");
        }
    }

    @Override
    public Programa programaConMayorAudiencia(String fecha) {
        try {
            // Creamos un document para buscar programas con una audiencia en una fecha determinada
            Document documentAudiencia = new Document("audiencia.fecha", fecha);
            // Ordenamos por espectadores descendente
            Document documentProgOrden = coleccion.find(documentAudiencia)
                    .sort(new Document("audiencia.espectadores", -1))
                    .first(); // Obtenemos el primer dato encontrado

            return Programa.fromDocument(documentProgOrden);

        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar el programa con mayor audiencia");
        }
    }

    @Override
    public double calcularAudienciaMedia(String id, String fechaInicio, String fechaFin) {
        try {
            // Buscamos el programa por su ID
            Document documentId = coleccion.find(new Document("_id", id)).first();
            // Obtenemos la lista de audiencias de dicho programa
            List<Document> audiencias = (List<Document>) documentId.get("audiencia");

            double sumaAudiencia = 0;
            int contadorAudiencia = 0;
            double resultado;

            // Recorremos las audiencias
            for (Document audiencia : audiencias) {
                String fecha = audiencia.getString("fecha");
                int espectadores = audiencia.getInteger("espectadores");

                // calculamos el promedio de audiencia en el rango de fechas
                if (fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0) {
                    sumaAudiencia += espectadores;
                    contadorAudiencia++;
                }
            }

            // Calculo del promedio
            resultado = sumaAudiencia / contadorAudiencia;

            return resultado;
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular la audiencia media");
        }
    }
}
