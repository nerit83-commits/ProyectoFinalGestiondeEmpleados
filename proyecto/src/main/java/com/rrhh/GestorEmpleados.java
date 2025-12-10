package com.rrhh;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class GestorEmpleados {

    // Arrays para almacenar empleados y evaluaciones.
    private List<Empleado> empleados = new ArrayList<>();
    private List<Evaluacion> evaluaciones = new ArrayList<>();

    public void agregarEmpleado(Empleado e) {  // Método para agregar un nuevo empleado.
        empleados.add(e);
    }

    public List<Empleado> getEmpleados() {  // Método para obtener la lista de empleados.
        return empleados;
    }

    public Empleado buscarPorNombre(String nombre) {  // Método para buscar un empleado por su nombre.
        return empleados.stream() 
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre)) // Ignora mayúsculas/minúsculas al comparar nombres.
                .findFirst()  //Busca el primer empleado que coincida con el nombre dado.
                .orElse(null);  // Devuelve null si no se encuentra ningún empleado con ese nombre.
    }

    // El public long se usa para devolver un valor numérico entero largo.
    public long contarActivosPorArea(String area) {  // Método para contar empleados activos en un área específica.
        return empleados.stream()
                .filter(e -> e.getArea().equalsIgnoreCase(area) && e.isActivo())  // Filtra empleados por área y estado activo.
                .count();  // Cuenta los empleados que cumplen con los criterios.
    }

    public void registrarEvaluacion(Empleado empleado, int mes, int año, int puntaje, String comentario) {  // Método para registrar una evaluación.
        Evaluacion ev = new Evaluacion(mes, año, puntaje, comentario, empleado.getSalario());  // Crea una nueva evaluación.
        evaluaciones.add(ev);  // Agrega la evaluación a la lista general.
        empleado.getEvaluaciones().add(ev);  // Agrega la evaluación a los registros del empleado.
    }

    public double obtenerPromedioEmpleado(Empleado empleado, int año) {  // Método para calcular el promedio anual de puntajes de un empleado.
        return empleado.getEvaluaciones().stream()  // Inicia un stream de las evaluaciones del empleado.
                .filter(e -> e.getAño() == año)  // Filtra las evaluaciones por el año especificado.
                .mapToInt(Evaluacion::getPuntaje)  // Mapea las evaluaciones filtradas seleccionando solo los puntajes.
                .average()  // Calcula el promedio de los puntajes.
                .orElse(0.0);  // Devuelve 0.0 si no hay evaluaciones para ese año.
    }
   
    public Empleado mejorEmpleadoDelAño(int año) {
        Empleado mejor = null;
        double mejorPromedio = -1;  // Inicializado a -1 para asegurar que cualquier promedio válido lo supere.
        String comentarioMejorEval = null;

        for (Empleado e : empleados) {
        // Filtrar evaluaciones del año
                List<Evaluacion> evalsDelAño = e.getEvaluaciones().stream()
                .filter(ev -> ev.getAño() == año)
                .collect(Collectors.toList());

             if (evalsDelAño.isEmpty()) {
            continue;  // Si no tiene evaluaciones ese año, se salta
            }

            // Calcular promedio de puntajes
            double promedio = evalsDelAño.stream()
                .mapToInt(Evaluacion::getPuntaje)
                .average()
                .orElse(0);

            // Obtener la evaluación con mayor puntaje
         Evaluacion mejorEvalEmpleado = evalsDelAño.stream()
                .max(Comparator.comparingInt(Evaluacion::getPuntaje))
                .orElse(null);

            // Si supera al mejor promedio actual, actualizar "mejor"
            if (promedio > mejorPromedio) {
                mejorPromedio = promedio;
                mejor = e;

                if (mejorEvalEmpleado != null) {
                    comentarioMejorEval = mejorEvalEmpleado.getComentario();
                 }
            }
        }

        // Mostrar el comentario cuando se muestra el reporte
        if (mejor != null) {
            System.out.println("========== REPORTE ==========");
            System.out.println(" Mejor empleado del año " + año + ": " + mejor.getNombre());
            System.out.println(" Promedio de desempeño: " + mejorPromedio);
            System.out.println(" Comentario destacado: " + comentarioMejorEval);
            System.out.println("=============================");
        }

        return mejor;
    }
}