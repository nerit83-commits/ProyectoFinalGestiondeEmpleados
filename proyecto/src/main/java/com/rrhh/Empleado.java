package com.rrhh;

import java.util.ArrayList;
import java.util.List;

public class Empleado {

    private String nombre;
    private String cargo;
    private String area;
    private double salario;
    private boolean activo;

    private List<Evaluacion> evaluaciones = new ArrayList<>();


    // Constructor
    public Empleado(String nombre, String cargo, String area, double salario) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.area = area;
        this.salario = salario;
        this.activo = true;
    }

    // Método para obtener las evaluaciones del empleado.
    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void agregarEvaluacion(Evaluacion evaluacion) {
    this.evaluaciones.add(evaluacion);
    }

    // Getters and Setters
    public String getNombre() { 
        return nombre; 
    }
    public String getCargo() { 
        return cargo; 
    }
    public String getArea() { 
        return area; 
    }
    public double getSalario() { 
        return salario; 
    }
    public boolean isActivo() { 
        return activo; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public void setCargo(String cargo) { 
        this.cargo = cargo; 
    }
    public void setArea(String area) { 
        this.area = area; 
    }
    public void setSalario(double salario) { 
        this.salario = salario; 
    }
    public void setActivo(boolean activo) { 
        this.activo = activo; 
    }

    
    @Override
    public String toString() { // Formato personalizado para mostrar la información del empleado.
        return nombre + " - " + cargo + " (" + area + ") - $" + salario +
                (activo ? " [ACTIVO]" : " [INACTIVO]");
    }
}