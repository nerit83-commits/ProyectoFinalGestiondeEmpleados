package com.rrhh;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GestorEmpleados {

    // Arrays dinámicos para almacenar empleados y evaluaciones.
    private List<Empleado> empleados = new ArrayList<>();  //Empleados es una lista que almacena objetos de tipo Empleado.
    private List<Evaluacion> evaluaciones = new ArrayList<>();  //Evaluaciones es una lista que almacena objetos de tipo Evaluacion.

    public void agregarEmpleado(Empleado e) {  // Método para agregar un nuevo empleado.
        empleados.add(e);  //inserta el empleado recibido al final de la lista de empleados.
                           //recordar que "e" es el parámetro que representa al empleado que se va a agregar.
    }

    public List<Empleado> getEmpleados() {  // Método para obtener la lista de empleados.
        return empleados;
    }

    public Empleado buscarPorNombre(String nombre) {  // Método para buscar un empleado por su nombre.
        return empleados.stream() 
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre)) // Ignora mayúsculas/minúsculas al comparar nombres.
                .findFirst()  //Busca el primer empleado que coincida con el nombre dado.
                .orElse(null);  // Devuelve null si no se encuentra ningún empleado con ese nombre.
    }  //Con este método se recorre la lista de empleados, busca el primer empleado cuyo nombre coincida con el que se pasó 
       // (sin importar mayúsculas), si lo encuentra: devuelve el empleado, si no: devuelve null.
       
       /*
       public Empleado buscarPorNombre(String nombre) {
            // Recorre la lista de empleados uno por uno
            for (Empleado e : empleados) {
                // Compara el nombre ignorando mayúsculas/minúsculas
                if (e.getNombre().equalsIgnoreCase(nombre)) {
                    // Si encuentra un empleado con ese nombre, lo devuelve
                    return e;
                }   
            }
            // Si termina el bucle sin encontrar ninguno, devuelve null
             return null;
        }
        */

    // El public long se usa para devolver un valor numérico entero largo.
    public long contarActivosPorArea(String area) {  // se trata de un método público que devuelve un número tipo contador (long). Recibe un parámetro String.
        return empleados.stream()  //utiliza un stream para recorrar la lista de empleados utilizando filtros.
                .filter(e -> e.getArea().equalsIgnoreCase(area) && e.isActivo())  // el filtro utiliza una condición que compara área del empleado y su estado.
                .count();  // Cuenta los empleados que cumplen con los criterios.
    } //la condición incluye que los empleados sean del área indicada y que además estén activos.

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
    } //Primero Obtiene la lista de evaluaciones del empleado y la convierte en un stream (un flujo de elementos),
    // permitiendo aplicar operaciones como filtrar, sumar, promediar. Luego filtra las evaluaciones con la condición del año indicado.
    //aplica mapToInt para extraer solo los puntajes de las evaluaciones filtradas.
    //luego calcula el promedio de esos puntajes con average()pero lo combina con orElse(0.0). Porque average() devuelve un OptionalDouble,
    //que puede no tener valor si no hay evaluaciones para ese año (ahí usa el orElse para manejar ese caso).

    /* 
    public double obtenerPromedioEmpleado(Empleado empleado, int año) {
        List<Evaluacion> evaluaciones = empleado.getEvaluaciones();
        int suma = 0;
        int cantidad = 0;

        for (Evaluacion ev : evaluaciones) {
            if (ev.getAño() == año) {
                suma += ev.getPuntaje();
                cantidad++;
            }
        }

        if (cantidad == 0) {
            return 0.0;
        }

        return (double) suma / cantidad;
    }
    //este public double obtenerPromedioEmpleado(Empleado empleado, int año) es un método que recibe:
    //un empleado, un año específico y devuelve un double (el promedio de puntaje de ese empleado en ese año).
    //utiliza List<Evaluacion> evaluaciones = empleado.getEvaluaciones(); porque cada empleado tiene su propia lista de evaluaciones.
    //precisamso obtenerla para poder recorrerla.Por ejemplo: [Evaluacion1, Evaluacion2, Evaluacion3...]
    */

    public List<Empleado> mejoresEmpleadosDelAño(int año) {
        List<Empleado> candidatos = new ArrayList<>();
        double mejorPromedio = -1;
        //candidatos: aquí se guardarán los empleados que tengan el mejor promedio.
        //mejorPromedio: empieza en -1 para que cualquier promedio real (0–100) sea mayor que él.

        for (Empleado e : empleados) {  //se recorre cada empleado registrado.

            if (!e.isActivo()) {  //si el empleado no está activo se salta. Si isActivo() devuelve false y no participa en la competición.
                continue; ///continue para pasar directamente al siguiente empleado.
            }   

            // Filtrar evaluaciones del año
            List<Evaluacion> evalsDelAño = e.getEvaluaciones().stream()  //lista todas las evaluaciones del empleado y con el stream abrimos un flujo.
                .filter(ev -> ev.getAño() == año)  //filtramos y nos quedamos solo con las evaluaciones del año indicado.
                .collect(Collectors.toList());   //convierte al stream nuevamente en una lista.

            if (evalsDelAño.isEmpty()) {  //si el empleado no tiene evaluaciones ese año, es ignorado.
                continue;
            }   

            double promedio = evalsDelAño.stream() //se calcula el promedio abriendo el stream.
                .mapToInt(Evaluacion::getPuntaje)  //extrae los puntajes como enteros.
                .average()  //calcula el promedio.
                .orElse(0); //si por algún motivo no hay evaluaciones (no debería pasar) devuelve 0.

            if (promedio > mejorPromedio) {  //comparamos el promedio con el mejor promedio registrado.
                mejorPromedio = promedio; //si el caso 1 supera al mejor promedio, lo actualiza,  
                candidatos.clear();  //limpia la lista de candidatos,
                candidatos.add(e);  //añade al nuevo mejor empleado.
            } else if (promedio == mejorPromedio) {  //pero si empata con el mejor promedio,
                candidatos.add(e);  //se añade sin eliminar quedando todos los empatados con mejor promedio.
            }
        }

        return candidatos;
    }
}