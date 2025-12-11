package com.rrhh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {

    @Test
    @Tag("smoke")
    public void testConstructorValoresCorrectos() {
        Empleado e = new Empleado("Ana", "Licenciada", "Enfermería", 80000);

        assertEquals("Ana", e.getNombre());
        assertEquals("Licenciada", e.getCargo());
        assertEquals("Enfermería", e.getArea());
        assertEquals(80000, e.getSalario());
        assertTrue(e.isActivo());
    }

    @Test
    @Tag("regression")
    public void testAgregarEvaluacion() {
        Empleado e = new Empleado("Luis", "Auxiliar", "Enfermería", 40000);

        Evaluacion ev = new Evaluacion(3, 2024, 4, "Muy bien", 40000);
        e.agregarEvaluacion(ev);

        assertEquals(1, e.getEvaluaciones().size());
        assertEquals(ev, e.getEvaluaciones().get(0));
    }

    @Test
    @Tag("regression")
    public void testNombreVacio() {
        Empleado e = new Empleado("", "Auxiliar", "Enfermería", 40000);
        assertEquals("", e.getNombre());
    }

    @Test
    @Tag("smoke")
    public void testToStringFormatoCorrecto() {
        Empleado e = new Empleado("Ana", "Licenciada", "Enfermería", 80000);

        String resultado = e.toString();

        assertTrue(resultado.contains("Ana"));
        assertTrue(resultado.contains("Licenciada"));
        assertTrue(resultado.contains("Enfermería"));
        assertTrue(resultado.contains("80000"));
        assertTrue(resultado.contains("[ACTIVO]"));
    }

    @ParameterizedTest
    @Tag("regression")
    @CsvSource({
            "Juan,Mensajero,Mensajería",
            "María,Supervisora,RRHH",
            "Pedro,Auxiliar,Servicios"
    })
    public void testSetters(String nombre, String cargo, String area) {
        Empleado e = new Empleado("X", "X", "X", 10000);
        // Aquí modifico los valores.
        e.setNombre(nombre);
        e.setCargo(cargo);
        e.setArea(area);
        // Pruebo las modificaciones.
        assertEquals(nombre, e.getNombre());
        assertEquals(cargo, e.getCargo());
        assertEquals(area, e.getArea());
    }

    @Test
    @Tag("regression")
    public void testSetActivo() {
        Empleado e = new Empleado("Ana", "Licenciada", "Enfermería", 80000);

        e.setActivo(false);  // Aquí modifico el estado.

        assertFalse(e.isActivo());  // si es false, la prueba pasa.
    }

    @Test
    @Tag("regression")
    public void testModificarSalario() {
        Empleado e = new Empleado("Ana", "Licenciada", "Enfermería", 80000);

        e.setSalario(52000);  // Aquí modifico el salario.

        assertEquals(52000, e.getSalario()); // Pruebo la modificación.
    }
}
