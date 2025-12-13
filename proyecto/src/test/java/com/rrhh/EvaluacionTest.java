package com.rrhh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class EvaluacionTest {

    @Test
    @Tag("smoke")  // Test smoke que sirve para verificar el constructor.
    public void testConstructorValido() {
        Evaluacion eval = new Evaluacion(5, 2024, 4, "Buen desempeño", 30000);
        // assertions para verificar que los valores se asignaron correctamente.
        assertEquals(5, eval.getMes());  
        assertEquals(2024, eval.getAño()); 
        assertEquals(4, eval.getPuntaje());
        assertEquals("Buen desempeño", eval.getComentario());
    }

    @Test
    @Tag("regression")  // Test regression verifica que el constructor muestre la excepción para un mes inválido.
    public void testConstructorMesInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Evaluacion(15, 2024, 3, "Fuera de rango", 30000));
    }

    @Test
    @Tag("regression")  // Test regression verifica que el constructor muestre excepción para un comentario vacío.
    public void testComentarioVacio() {
        Evaluacion e = new Evaluacion(3, 2024, 4, "", 20000);
        assertEquals("", e.getComentario());
    }

    @ParameterizedTest  // Estos tests parametrizados verifican el constructor con diferentes conjuntos de datos.
    @Tag("regression")  // Usando regression seguramos que los cambios futuros no rompan la funcionalidad existente.
    @CsvSource({
        // Mes, Año, Puntaje, Comentario
            "1, 2024,   5,    Excelente",
            "6, 2023,   3,    Correcto",
           "12, 2022,   2,    Debe mejorar"
    })
    public void testConstructorParametrizado(int mes, int año, int puntaje, String comentario) {
        Evaluacion eval = new Evaluacion(mes, año, puntaje, comentario, 20000);
        // assertions para verificar mes, año, puntaje y comentario.
        assertEquals(mes, eval.getMes());
        assertEquals(año, eval.getAño());
        assertEquals(puntaje, eval.getPuntaje());
        assertEquals(comentario, eval.getComentario());
    }
}