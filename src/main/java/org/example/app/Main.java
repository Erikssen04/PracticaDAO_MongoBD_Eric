package org.example.app;

import org.example.dao.ProgramaDAOMongoDB;
import org.example.modelo.Audiencia;
import org.example.modelo.Colaborador;
import org.example.modelo.Horario;
import org.example.modelo.Programa;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProgramaDAOMongoDB dao = new ProgramaDAOMongoDB();

        String menu = "Seleccione una opción válida: \n" +
                "1. Crear un nuevo programa \n" +
                "2. Consultar todos los programas \n" +
                "3. Consultar un programa específico por su ID o nombre \n" +
                "4. Actualizar un programa \n" +
                "5. Eliminar un programa \n" +
                "6. Listar programas de una categoria específica \n" +
                "7. Obtener el programa con mayor audiencia en una fecha concreta \n" +
                "8. Calcular la audiencia media de un programa en un rango de fechas  \n" +
                "0. Salir";

        boolean opcionValida = false;
        int opcion;

        // Bucle que muestra el menú y solo sale de la aplicación si la opción no corresponde a la condición
        do{
            try {
                System.out.println(menu);
                opcion = sc.nextInt();

                if (opcion >= 0 && opcion <= 8) {
                    opcionValida = true;
                    sc.nextLine();

                    // Se ejecutará la opción que hayamos introducido
                    switch (opcion) {
                        case 1:
                            System.out.println("Introduzca ID del programa: ");
                            String id = sc.next();
                            sc.nextLine();

                            System.out.println("Introduzca nombre del programa: ");
                            String nombre = sc.nextLine();

                            System.out.println("Introduzca categoría del programa: ");
                            String categoria = sc.nextLine();

                            System.out.println("Introduzca el número de horarios: ");
                            int numHorarios = sc.nextInt();
                            sc.nextLine();

                            List<Horario> horarios = new ArrayList<>();

                            for (int i = 1; i <= numHorarios; i++) {
                                System.out.println("Horario Nº "+(i)+" - Introduzca el día de la semana: ");
                                String dia = sc.next();
                                sc.nextLine();

                                System.out.println("Horario Nº "+(i)+" - Introduzca la hora: ");
                                String hora = sc.nextLine();

                                horarios.add(new Horario(dia, hora));
                            }

                            System.out.println("Introduzca el número de audiencias: ");
                            int numAudiencias = sc.nextInt();
                            sc.nextLine();

                            List<Audiencia> audiencias = new ArrayList<>();

                            for (int i = 1; i <= numAudiencias; i++) {
                                System.out.println("Audiencia Nº "+(i)+" - Introduzca la fecha: ");
                                String fecha = sc.next();

                                System.out.println("Audiencia Nº "+(i)+" - Introduzca los espectadores: ");
                                int espectadores = sc.nextInt();

                                audiencias.add(new Audiencia(fecha, espectadores));
                            }

                            System.out.println("Introduzca el número de colaboradores: ");
                            int numColaboradores = sc.nextInt();
                            sc.nextLine();

                            List<Colaborador> colaboradores = new ArrayList<>();

                            for (int i = 1; i <= numColaboradores; i++) {
                                System.out.println("Colaborador Nº "+(i)+" - Introduzca su nombre: ");
                                String nombreCol = sc.nextLine();

                                System.out.println("Colaborador Nº "+(i)+" - Introduzca su rol: ");
                                String rol = sc.nextLine();

                                colaboradores.add(new Colaborador(nombreCol, rol));
                            }

                            Programa nuevoPrograma = new Programa(
                                    id, nombre, categoria, horarios, audiencias, colaboradores);

                            dao.insertar(nuevoPrograma);

                            System.out.println("¡Programa creado exitosamente!");

                            break;
                        case 2:
                            List<Programa> programas = dao.findAll();
                            for (Programa programa : programas){
                                System.out.println(programa.toString());
                            }

                            System.out.println("¡Programas mostrados exitosamente!");

                            break;
                        case 3:
                            System.out.println("Introduzca ID del programa: ");
                            String id4 = sc.next();

                            Programa programa = dao.findById(id4);

                            System.out.println(programa.toString());
                            System.out.println("¡Programa "+programa.getNombre()+" obtenido exitosamente!");

                            break;
                        case 4:
                            System.out.println("Introduzca ID del programa: ");
                            String id2 = sc.next();
                            sc.nextLine();

                            System.out.println("Introduzca nombre del programa: ");
                            String nombre2 = sc.nextLine();

                            System.out.println("Introduzca categoría del programa: ");
                            String categoria2 = sc.nextLine();

                            System.out.println("Introduzca el número de horarios: ");
                            int numHorarios2 = sc.nextInt();
                            sc.nextLine();

                            List<Horario> horarios2 = new ArrayList<>();

                            for (int i = 1; i <= numHorarios2; i++) {
                                System.out.println("Horario Nº "+(i)+" - Introduzca el día de la semana: ");
                                String dia = sc.next();
                                sc.nextLine();

                                System.out.println("Horario Nº "+(i)+" - Introduzca la hora: ");
                                String hora = sc.nextLine();

                                horarios2.add(new Horario(dia, hora));
                            }

                            System.out.println("Introduzca el número de audiencias: ");
                            int numAudiencias2 = sc.nextInt();
                            sc.nextLine();

                            List<Audiencia> audiencias2 = new ArrayList<>();

                            for (int i = 1; i <= numAudiencias2; i++) {
                                System.out.println("Audiencia Nº "+(i)+" - Introduzca la fecha: ");
                                String fecha = sc.next();

                                System.out.println("Audiencia Nº "+(i)+" - Introduzca los espectadores: ");
                                int espectadores = sc.nextInt();

                                audiencias2.add(new Audiencia(fecha, espectadores));
                            }

                            System.out.println("Introduzca el número de colaboradores: ");
                            int numColaboradores2 = sc.nextInt();
                            sc.nextLine();

                            List<Colaborador> colaboradores2 = new ArrayList<>();

                            for (int i = 1; i <= numColaboradores2; i++) {
                                System.out.println("Colaborador Nº "+(i)+" - Introduzca su nombre: ");
                                String nombreCol = sc.nextLine();

                                System.out.println("Colaborador Nº "+(i)+" - Introduzca su rol: ");
                                String rol = sc.nextLine();

                                colaboradores2.add(new Colaborador(nombreCol, rol));
                            }

                            dao.actualizar(new Programa(
                                    id2, nombre2, categoria2, horarios2, audiencias2, colaboradores2));

                            System.out.println("¡Programa actualizado exitosamente!");
                            break;
                        case 5:
                            System.out.println("Introduzca ID del programa: ");
                            String id3 = sc.next();

                            dao.eliminar(id3);

                            System.out.println("¡Programa eliminado exitosamente!");

                            break;
                        case 6:
                            System.out.println("Introduzca categoria del programa: ");
                            String categoria3 = sc.next();

                            List<Programa> programasByCategoria = dao.findByCategoria(categoria3);
                            for (Programa programa1 : programasByCategoria){
                                System.out.println(programa1.toString());
                            }

                            System.out.println("¡Programas de categoria "+categoria3+" mostrados exitosamente!");

                            break;
                        case 7:
                            System.out.println("Introduzca fecha del programa: ");
                            String fecha = sc.next();

                            Programa programa2 = dao.programaConMayorAudiencia(fecha);

                            System.out.println(programa2.toString());
                            System.out.println("¡Programa "+programa2.getNombre()+" obtenido exitosamente!");

                            break;
                        case 8:
                            System.out.println("Introduzca ID del programa: ");
                            String id5 = sc.nextLine();

                            System.out.println("Introduzca fecha inicio: ");
                            String fechaIni = sc.nextLine();

                            System.out.println("Introduzca fecha fin: ");
                            String fechaFin = sc.nextLine();

                            System.out.println("Audiencia media del programa con ID "+id5+ " es: "+
                                    dao.calcularAudienciaMedia(id5,fechaIni,fechaFin));

                            break;
                        case 0:
                            System.out.println("Se ha decidido salir del programa");
                            opcionValida = false;
                            System.exit(0);

                            break;
                        default:
                            System.out.println();

                            break;
                    }
                } else {
                    System.out.println("Por favor, ingrese una opción válida");
                }
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(("Los datos introducidos no son válidos, por favor intente de nuevo"));
            } catch (InputMismatchException f) {
                System.out.println(("Acabas de colocar un tipo de dato que no corresponde a lo que se ha pedido"));
                opcionValida=false;
            }
        } while (opcionValida);
    }
}