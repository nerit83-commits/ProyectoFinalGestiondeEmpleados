package com.rrhh;

public class Evaluacion {

    private int mes;    // Mes del 1 al 12.
    private int año;
    private int puntaje;   // Puntaje entre 1 y 5.
    private String comentario;  // Comentario del supervisor que evalúa.
    //private double bonificacion;  // La bonificación se calcula en el constructor.

    // Constructor
    public Evaluacion(int mes, int año, int puntaje, String comentario, double salarioEmpleado) {

        // If para validar mes y puntaje. Manejo de excepciones.
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mes inválido. Debe ser 1-12.");
        }
        if (puntaje < 1 || puntaje > 5) {
            throw new IllegalArgumentException("Puntaje inválido. Debe ser 1-5.");
        }

        this.mes = mes;
        this.año = año;
        this.puntaje = puntaje;
        this.comentario = comentario;
        //this.bonificacion = calcularBonificacion(puntaje, salarioEmpleado);
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

    @Override
    public String toString() {
        return "Evaluacion{" +
                "mes=" + mes +
                ", año=" + año +
                ", puntaje=" + puntaje +
                ", comentario='" + comentario + '\'' +
                '}';
    }

}