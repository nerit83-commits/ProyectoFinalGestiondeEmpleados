package com.rrhh;

public class Evaluacion {

    private int mes;    // Mes del 1 al 12.
    private int año;
    private int puntaje;   // Puntaje entre 1 y 5.
    private String comentario;  // Comentario del supervisor que evalúa.
    private double bonificacion;  // La bonificación se calcula en el constructor.

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
        this.bonificacion = calcularBonificacion(puntaje, salarioEmpleado);
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
    public double getBonificacion() { 
        return bonificacion; 
    }


    // Método privado para calcular la bonificación basada en el puntaje.
     /*
     * Tabla:
     * 5 = +20%
     * 4 = +10%
     * 3 =  0%
     * 2 = -10%
     * 1 = -20%
     */
    private double calcularBonificacion(int puntaje, double salario) {
        switch (puntaje) {  //Switch que asigna bonificaciones segun el puntaje obtenido.
            case 5:
            return salario * 0.20;    // 20% de bonificación
            case 4:
            return salario * 0.10;   // 10% de bonificación
            case 3:
            return 0.0;              // Sin bonificación
            case 2:
            return salario * -0.10;  // 10% de deducción
            case 1:
            return salario * -0.20;  // 20% de deducción
            default:
            return 0.0;             // Puntaje inválido, por lo tanto, sin bonificación   
        }
    }

    @Override
    public String toString() {
        return "Evaluacion{" +
                "mes=" + mes +
                ", año=" + año +
                ", puntaje=" + puntaje +
                ", comentario='" + comentario + '\'' +
                ", bonificacion=" + bonificacion +
                '}';
    }

}