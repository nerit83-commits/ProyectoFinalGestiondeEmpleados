package com.rrhh;

public class Evaluacion {

    private int mes;    // Mes del 1 al 12.
    private int año;
    private int puntaje;   // Puntaje entre 1 y 5.
    private String comentario;  // Comentario del supervisor que evalúa.
    private double salarioEmpleado; // Salario del empleado evaluado.


    // Constructor.
    public Evaluacion(int mes, int año, int puntaje, String comentario, double salarioEmpleado) {

        // If para validar mes y puntaje. Manejo de excepciones.
        if (mes < 1 || mes > 12) {  //si el mes es menor que 1 ó mayor que 12, entonces es un mes inválido.
            throw new IllegalArgumentException("Mes inválido. Debe ser 1-12.");  //con esto lanzo la excepción indicando el error.
        }
        if (puntaje < 1 || puntaje > 5) {
            throw new IllegalArgumentException("Puntaje inválido. Debe ser 1-5.");
        }
        // Aquí guardo los valores.
        this.mes = mes;
        this.año = año;
        this.puntaje = puntaje;
        this.comentario = comentario;
        this.salarioEmpleado = salarioEmpleado;
      
    }


    // Getters
    public int getMes() { 
        return mes; 
    }
    public int getAño() { 
        return año; 
    }
    public int getPuntaje() { 
        return puntaje; 
    }
    public String getComentario() { 
        return comentario; 
    }
    public double getSalarioEmpleado() { 
        return salarioEmpleado; 
    }

    
    @Override
    public String toString() {  //Método para mostrar la evaluacion con sus atributos.
        return "Evaluacion{" +
                "mes=" + mes +
                ", año=" + año +
                ", puntaje=" + puntaje +
                ", comentario='" + comentario + '\'' + 
                '}';
    }

}