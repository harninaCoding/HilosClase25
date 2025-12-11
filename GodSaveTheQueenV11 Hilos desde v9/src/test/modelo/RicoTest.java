package test.modelo;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.escenario.CuerpoPolicia;
import modelo.escenario.Posicion;
import modelo.personajes.Policia;
import modelo.personajes.Ratero;
import modelo.personajes.Rico;

public class RicoTest {
    Rico rico;
    CuerpoPolicia cuerpoPolicia;
    Ratero ratero;
    private List<Policia> policias = new ArrayList<>();
    private List<Rico> ricos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        rico = new Rico();
        policias.add(new Policia());
        ricos.add(rico);
    }

    @Test
    void testAtracando() {
        ratero = new Ratero(new Posicion(0, 0), 10, 100, null, policias);
        assertTrue(rico.isActivo());
        rico.atracando(ratero);
        assertTrue(!rico.isActivo());
    }

    @Test
    void testHacerUnMovimiento() {
        Posicion posicion = rico.getPosicion();
        rico.hacerUnMovimiento();
        assertNotEquals(posicion, rico.getPosicion());
    }
}
