package com.rrhh;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Instancia del gestor de empleados.
        GestorEmpleados gestor = new GestorEmpleados();
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Agrego un bucle simple de menú para interactuar con el sistema.
        do {  
            System.out.println("\n=== SISTEMA RRHH ===");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Registrar evaluación");
            System.out.println("3. Reportes");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar salto

            // Opciones del menú.
            switch (opcion) {

                // Caso para agregar un empleado.
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Cargo: ");
                    String cargo = sc.nextLine();

                    System.out.print("Área: ");
                    String area = sc.nextLine();

                    System.out.print("Salario: ");
                    double salario = sc.nextDouble();
                    sc.nextLine();

                    gestor.agregarEmpleado(new Empleado(nombre, cargo, area, salario));
                    System.out.println("Empleado agregado.");
                    break;

                // Caso para registrar una evaluación.
                case 2:
                    System.out.print("Nombre del empleado: ");
                    String nombreEmp = sc.nextLine();

                    Empleado e = gestor.buscarPorNombre(nombreEmp);

                    if (e == null) {
                        System.out.println("Empleado no encontrado");
                        break;
                    }

                    System.out.print("Mes (del 1 al 12): ");
                    int mes = sc.nextInt();

                    System.out.print("Año: ");
                    int anioEv = sc.nextInt();

                    System.out.print("Puntaje (1-5): ");
                    int puntaje = sc.nextInt();
                    sc.nextLine(); // limpiar

                    System.out.print("Comentario: ");
                    String comentario = sc.nextLine();

                    gestor.registrarEvaluacion(e, mes, anioEv, puntaje, comentario);
                    System.out.println("Evaluación registrada.");
                    break;
                
                // Caso para conocer el total de bonos y el mejor empleado del año.
                case 3:
                    System.out.print("Año: ");
                    int anioReporte = sc.nextInt();

                    System.out.println("Total bonos en el año: " + gestor.totalBonosAnuales(anioReporte));
                    System.out.println("Mejor empleado del año: " + gestor.mejorEmpleadoDelAño(anioReporte));
                    break;
                // Cierre del sistema.
                case 0:
                    System.out.println("Fin del sistema.");
                    break;
                // Caso por defecto para opciones inválidas.
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);  //Este while mantiene el menú activo hasta que el usuario decida salir.
    }
}