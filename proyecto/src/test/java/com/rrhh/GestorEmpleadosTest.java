package com.rrhh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class GestorEmpleadosTest {

    @Test
    @Tag("smoke")  // Test smoke para verificar la adición de un empleado.
    public void testAgregarEmpleado() {
        GestorEmpleados gestor = new GestorEmpleados();
        Empleado e = new Empleado("Ana", "Analista", "IT", 30000);

        gestor.agregarEmpleado(e);  // Agrega el empleado al gestor.

        assertEquals(1, gestor.getEmpleados().size()); // Verifica que el tamaño de la lista sea 1.
    }

    @Test
    @Tag("regression")  // Con este test regression muestra la búsqueda de empleado por nombre vacío.
    public void testBuscarEmpleadoNombreVacio() {
        GestorEmpleados gestor = new GestorEmpleados();

        assertNull(gestor.buscarPorNombre(""));  // Verifica que la búsqueda de un nombre vacío devuelva null.
    }

    @ParameterizedTest
    @Tag("regression")
    @CsvSource({
            "4,5,3,4.0",
            "5,5,5,5.0",
            "1,2,3,2.0"
    })
    public void testPromedioEmpleadoParametrizado(int p1, int p2, int p3, double esperado) {
        GestorEmpleados gestor = new GestorEmpleados();
        Empleado emp = new Empleado("Luis", "Dev", "IT", 40000);

        gestor.agregarEmpleado(emp);

        gestor.registrarEvaluacion(emp, 1, 2024, p1, "OK");
        gestor.registrarEvaluacion(emp, 2, 2024, p2, "OK");
        gestor.registrarEvaluacion(emp, 3, 2024, p3, "OK");

        double prom = gestor.obtenerPromedioEmpleado(emp, 2024);

        assertEquals(esperado, prom);
    }

    @Test
    @Tag("regression")
    public void testMejorEmpleadoDelAño() {
        GestorEmpleados gestor = new GestorEmpleados();

        Empleado e1 = new Empleado("Ana", "Dev", "IT", 30000);
        Empleado e2 = new Empleado("Luis", "QA", "IT", 30000);

        gestor.agregarEmpleado(e1);
        gestor.agregarEmpleado(e2);

        gestor.registrarEvaluacion(e1, 1, 2024, 3, "Bien");
        gestor.registrarEvaluacion(e2, 1, 2024, 5, "Excelente");

        Empleado mejor = gestor.mejorEmpleadoDelAño(2024);

        assertEquals("Luis", mejor.getNombre());
    }
}