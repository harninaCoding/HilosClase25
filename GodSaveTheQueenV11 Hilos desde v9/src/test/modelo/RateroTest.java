package test.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.escenario.CuerpoPolicia;
import modelo.escenario.Posicion;
import modelo.personajes.EstadoRatero;
import modelo.personajes.Policia;
import modelo.personajes.Ratero;
import modelo.personajes.Rico;

public class RateroTest {

    Ratero ratero;
    private List<Rico> ricos;
    private List<Policia> policias;
    CuerpoPolicia cuerpoPolicia;

    @BeforeEach
    void setUp() {
        ricos = new ArrayList<>();
        policias = new ArrayList<>();
        policias.add(new Policia());
        cuerpoPolicia = new CuerpoPolicia();
        cuerpoPolicia.setCuerpoPolicia(ricos,policias);
        ricos.add(new Rico());
        ratero = new Ratero(new Posicion(0, 0), 10, 100, ricos, policias);
    }

    @Test
    void testAlarma() {
        int velocidad = ratero.getVelocidad();
        ratero.alarma();
        assertTrue(velocidad < ratero.getVelocidad());
    }

    @Test
    void testAtracar() {
        int botin = ratero.getBotin();
        ratero.setRicoObjetivoActual(ricos.get(0));
        ratero.atracar();
        assertTrue(botin < ratero.getBotin());
    }

    @Test
    void testComenzarDia() {
        ratero.comenzarDia();
        assertEquals(ratero.getRicoObjetivoActual(), Optional.empty());
        assertEquals(ratero.getPosicion(), ratero.getPosicionRefugio());
    }

    @Test
    void testControlarPolicia() {
        int radioAccion = ratero.getRadioAccion();
        Posicion posicion = new Posicion(50, 50);
        policias.get(0).setPosicion(posicion);
        ratero.setPosicion(new Posicion(posicion.getX() + radioAccion / 2, posicion.getY() + radioAccion / 2));
        assertTrue(ratero.controlarPolicia(policias));
        Posicion posicionActual = new Posicion(posicion.getX() + radioAccion + 1, posicion.getY() + radioAccion + 1);
        ratero.setPosicion(posicionActual);
        assertFalse(ratero.controlarPolicia(policias));
    }

    @Test
    void testDeambular() {
        Posicion posicion = ratero.getPosicion();
        for (int i = 0; i < 10; i++) {
            ratero.deambular();
            assertNotEquals(posicion, ratero.getPosicion());
            posicion = ratero.getPosicion();
        }
    }

    @Test
    void testEstablecerRicosActivosObjetivo() {
        Posicion posicion = new Posicion(50, 50);
        Posicion posicion2 = new Posicion(54, 54);
        Posicion posicion3 = new Posicion(64, 64);
        ricos.get(0).setPosicion(posicion);
        int radioAccion = ratero.getRadioAccion();
        ratero.setPosicion(new Posicion(posicion.getX() + radioAccion / 2, posicion.getY() + radioAccion / 2));
        List<Rico> establecerRicosActivosObjetivo = ratero.establecerRicosActivosObjetivo(ricos);
        assertEquals(establecerRicosActivosObjetivo.size(), 1);
        ricos.add(new Rico()); 
        ricos.get(1).setPosicion(posicion2);
        establecerRicosActivosObjetivo = ratero.establecerRicosActivosObjetivo(ricos);
        assertEquals(establecerRicosActivosObjetivo.size(), 2);
        ricos.add(new Rico());
        ricos.get(2).setPosicion(posicion3);
        establecerRicosActivosObjetivo = ratero.establecerRicosActivosObjetivo(ricos);
        assertEquals(establecerRicosActivosObjetivo.size(), 2);
    }

    @Test
    void testHacerUnMovimiento() {
        assertEquals(ratero.getEstado(), EstadoRatero.BUSCANDO);
        Posicion posicion = new Posicion(50, 50);
        ricos.get(0).setPosicion(posicion);
        int radioAccion = ratero.getRadioAccion();
        ratero.setPosicion(new Posicion(posicion.getX() + radioAccion / 2, posicion.getY() + radioAccion / 2));
        ratero.hacerUnMovimiento();
        assertEquals(ratero.getEstado(), EstadoRatero.PERSIGUIENDO_OBJETIVO);
        ratero.setPosicion(posicion);
        ratero.hacerUnMovimiento();
        assertTrue(ratero.getEstado() == EstadoRatero.HUYENDO||ratero.getEstado() == EstadoRatero.BUSCANDO);   
        ratero.hacerUnMovimiento();
        if(ratero.getEstado() == EstadoRatero.HUYENDO&&!ratero.isBotinSuficiente()) {
           int velocidad = ratero.getVelocidad();
           assertTrue(velocidad == 2);
        } 
        ratero.setPosicion(ratero.getPosicionRefugio());
        ratero.hacerUnMovimiento();
        assertTrue(ratero.getEstado() == EstadoRatero.INACTIVO);
    }

    @Test
    void testMoverA() {
        Posicion posicion = new Posicion(50, 50);
        Posicion posicion2 = new Posicion(62, 51);
        int distancia=Math.abs(posicion.getX()+posicion.getY()-posicion2.getX()-posicion2.getY());
        ratero.setPosicion(posicion);
        for (int i = 0; i < distancia; i++) {
          ratero.moverA(posicion2); 
        }
        assertEquals(ratero.getPosicion(), posicion2);
    }

    @Test
    void testSeleccionarRicoObjetivoCercanoSinVigilancia() {
        Posicion posicion = new Posicion(50, 50);
        Posicion posicion2 = new Posicion(54, 54);
        Posicion posicion3 = new Posicion(84, 84);
        ricos.get(0).setPosicion(posicion);
        int radioAccion = ratero.getRadioAccion();
        ratero.setPosicion(new Posicion(posicion.getX() + radioAccion / 2, posicion.getY() + radioAccion / 2));
        //posicion 55,55
        ricos.add(new Rico());
        ricos.get(1).setPosicion(posicion2);
        ricos.add(new Rico());
        ricos.get(2).setPosicion(posicion3);
        policias.get(0).setPosicion(posicion3);
        ratero.seleccionarRicoObjetivoCercanoSinVigilancia(ricos, policias);
        assertEquals(ricos.get(1), ratero.getRicoObjetivoActual().get());
        ratero.setPosicion(new Posicion(51,51));
        ratero.seleccionarRicoObjetivoCercanoSinVigilancia(ricos, policias);
        ratero.setPosicion(new Posicion(51,51));
        assertEquals(ricos.get(0), ratero.getRicoObjetivoActual().get());
        policias.get(0).setPosicion(new Posicion(62,62));
        ratero.seleccionarRicoObjetivoCercanoSinVigilancia(ricos, policias);
        assertEquals(ricos.get(0), ratero.getRicoObjetivoActual().get());
    }

}
