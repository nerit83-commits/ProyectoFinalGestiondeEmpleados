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
        Empleado e = new Empleado("Ana", "Desarrolladora", "IT", 45000);

        assertEquals("Ana", e.getNombre());
        assertEquals("Desarrolladora", e.getCargo());
        assertEquals("IT", e.getArea());
        assertEquals(45000, e.getSalario());
        assertTrue(e.isActivo());
    }

    @Test
    @Tag("regression")
    public void testAgregarEvaluacion() {
        Empleado e = new Empleado("Luis", "QA", "IT", 38000);

        Evaluacion ev = new Evaluacion(3, 2024, 4, "Muy bien", 38000);
        e.agregarEvaluacion(ev);

        assertEquals(1, e.getEvaluaciones().size());
        assertEquals(ev, e.getEvaluaciones().get(0));
    }

    @Test
    @Tag("regression")
    public void testNombreVacio() {
        Empleado e = new Empleado("", "Dev", "IT", 30000);
        assertEquals("", e.getNombre());
    }

    @Test
    @Tag("smoke")
    public void testToStringFormatoCorrecto() {
        Empleado e = new Empleado("Ana", "Dev", "IT", 50000);

        String resultado = e.toString();

        assertTrue(resultado.contains("Ana"));
        assertTrue(resultado.contains("Dev"));
        assertTrue(resultado.contains("IT"));
        assertTrue(resultado.contains("50000"));
        assertTrue(resultado.contains("[ACTIVO]"));
    }

    @ParameterizedTest
    @Tag("regression")
    @CsvSource({
            "Juan,Analista,Contabilidad",
            "María,Supervisora,RRHH",
            "Pedro,Técnico,Soporte"
    })
    public void testSetters(String nombre, String cargo, String area) {
        Empleado e = new Empleado("X", "X", "X", 10000);

        e.setNombre(nombre);
        e.setCargo(cargo);
        e.setArea(area);

        assertEquals(nombre, e.getNombre());
        assertEquals(cargo, e.getCargo());
        assertEquals(area, e.getArea());
    }

    @Test
    @Tag("regression")
    public void testSetActivo() {
        Empleado e = new Empleado("Ana", "Dev", "IT", 40000);

        e.setActivo(false);

        assertFalse(e.isActivo());
    }

    @Test
    @Tag("regression")
    public void testModificarSalario() {
        Empleado e = new Empleado("Ana", "Dev", "IT", 40000);

        e.setSalario(52000);

        assertEquals(52000, e.getSalario());
    }
}
