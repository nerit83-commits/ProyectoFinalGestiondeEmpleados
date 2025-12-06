package com.rrhh;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public double promedioAnual(Empleado empleado, int año) {  // Método para calcular el promedio anual de puntajes de un empleado.
        return empleado.getEvaluaciones().stream()  // Inicia un stream de las evaluaciones del empleado.
                .filter(e -> e.getAño() == año)  // Filtra las evaluaciones por el año especificado.
                .mapToInt(Evaluacion::getPuntaje)  // Mapea las evaluaciones filtradas seleccionando solo los puntajes.
                .average()  // Calcula el promedio de los puntajes.
                .orElse(0.0);  // Devuelve 0.0 si no hay evaluaciones para ese año.
    }

    public double totalBonosAnuales(int año) {
        return evaluaciones.stream()
                .filter(e -> e.getAño() == año)
                .mapToDouble(Evaluacion::getBonificacion)
                .sum();  // Suma todas las bonificaciones de las evaluaciones filtradas por año.
    }

   
    public Empleado mejorEmpleadoDelAño(int año) {
    Empleado mejor = null;
    double mejorPromedio = -1;  // Inicializado a -1 para asegurar que cualquier promedio válido lo supere.

    for (Empleado e : empleados) {
        // Filtrar evaluaciones del año
        List<Evaluacion> evalsDelAño = e.getEvaluaciones().stream()
                .filter(ev -> ev.getAño() == año)
                .collect(Collectors.toList());

        // Si no tiene evaluaciones ese año, pasamos al siguiente
        if (evalsDelAño.isEmpty()) {
            continue;
        }
       
        // Calcular el promedio del puntaje
        double promedio = evalsDelAño.stream()
                .mapToInt(Evaluacion::getPuntaje)
                .average()
                .orElse(0);

        // Ver si es mejor que el actual
        if (promedio > mejorPromedio) {
            mejorPromedio = promedio;
            mejor = e;
        }
    }

    return mejor; // Puede ser null si nadie tiene evaluaciones ese año
}
}