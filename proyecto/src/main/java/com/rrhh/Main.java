package com.rrhh;

import java.util.Comparator;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Instancia del gestor de empleados.
        GestorEmpleados gestor = new GestorEmpleados();
        Scanner sc = new Scanner(System.in);
        int opcion = -1;  // se utiliza -1 para iniciar el bucle

        // Agrego un bucle simple de menú para interactuar con el sistema.
        do {  
            System.out.println("\n=== SISTEMA RRHH ===");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Registrar evaluación");
            System.out.println("3. Reportes");
            System.out.println("4. Dar de baja (inactivar) un empleado");
            System.out.println("5. Activar un empleado");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            // Manejo de error para el menú "Opción".
            if (!sc.hasNextInt()) {  // verifico que la entrada sea un entero.
                System.out.println("Error: debe ingresar un número.");
                sc.nextLine();  // limpiar entrada inválida
                continue;       // volver al menú sin ejecutar el switch
            }
            opcion = sc.nextInt();
            sc.nextLine();  // limpia.

            // Opciones del menú.
            switch (opcion) {
                // Caso para agregar un empleado.
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Cargo: ");
                    String cargo = sc.nextLine();

                    System.out.print("Area: ");
                    String area = sc.nextLine();

                    System.out.print("Salario: ");
                    double salario = sc.nextDouble();
                    sc.nextLine();

                    gestor.agregarEmpleado(new Empleado(nombre, cargo, area, salario));
                    System.out.println("Empleado agregado correctamente como ACTIVO.");
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

                    if (!e.isActivo()) {  // si el empleado no está activo.
                        System.out.println("El empleado está INACTIVO, no se pueden registrar evaluaciones.");
                        break;
                    }

                    System.out.print("Mes (del 1 al 12): ");
                    int mes = sc.nextInt();

                    System.out.print("Año: ");
                    int añoEv = sc.nextInt();

                    System.out.print("Puntaje (1-5): ");
                    int puntaje = sc.nextInt();
                    sc.nextLine(); // limpiar

                    System.out.print("Comentario: ");
                    String comentario = sc.nextLine();

                    gestor.registrarEvaluacion(e, mes, añoEv, puntaje, comentario);
                    System.out.println("Evaluación registrada.");
                    break;
                
                // Caso para conocer el mejor empleado del año.
                case 3:
                    System.out.print("Año para consultar: ");
                    int añoReporte = sc.nextInt();
                    sc.nextLine();
                    // Array para guardar los mejores empleados verificando empates.
                    List<Empleado> mejores = gestor.mejoresEmpleadosDelAño(añoReporte);  

                    if (mejores.isEmpty()) {
                        System.out.println("No hay empleados activos con evaluaciones ese año " + añoReporte + ".");
                        break;
                    }

                    // Para mostrar el reporte.
                    System.out.println("\n===== REPORTE DE MEJORES EMPLEADOS DEL AÑO " + añoReporte + " =====");
                    
                    for (Empleado emp : mejores) {

                        double promedio = gestor.obtenerPromedioEmpleado(emp, añoReporte);
                        
                        String comentarioDestacado = emp.getEvaluaciones().stream()
                            .filter(ev -> ev.getAño() == añoReporte)
                            .max(Comparator.comparingInt(Evaluacion::getPuntaje))
                            .map(Evaluacion::getComentario)
                            .orElse("Sin comentarios");

                        System.out.println("\nEmpleado: " + emp.getNombre());
                        System.out.println("Promedio: " + promedio);
                        System.out.println("Comentario destacado: " + comentarioDestacado);
                    }
                    System.out.println("==============================================");
                    break;

                // Caso para inactivar un empleado.
                case 4:
                    System.out.print("Nombre del empleado: ");
                    String nomInac = sc.nextLine(); 

                    Empleado empInac = gestor.buscarPorNombre(nomInac);

                    if (empInac == null) {
                        System.out.println("Empleado no encontrado");
                        break;
                    }

                    empInac.setActivo(false);  // Acá se guarda el cambio de estado a inactivo.
                    System.out.println("Empleado inactivado correctamente.");
                    break;

                // Caso para activar un empleado.
                case 5:
                    System.out.print("Nombre del empleado: ");
                    String nomAct = sc.nextLine();

                    Empleado empAct = gestor.buscarPorNombre(nomAct);

                    if (empAct == null) {
                        System.out.println("Empleado no encontrado");
                        break;
                    }

                    empAct.setActivo(true);  // Aquí se guarda el cambio de estado a activo.
                    System.out.println("Empleado activado correctamente.");
                    break;

                // Cierre del sistema.
                case 0:
                    System.out.println("Fin del SISTEMA RRHH.");
                    break;
                    // Caso por defecto para opciones inválidas.
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);  //Este while mantiene el menú activo mientras la opción sea diferente a 0.

        sc.close();
    }   
}
