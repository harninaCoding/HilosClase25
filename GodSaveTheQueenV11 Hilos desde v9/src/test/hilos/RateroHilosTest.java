package test.hilos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static utiles.ShowMessages.showMessage;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import modelo.escenario.Barrio;
import modelo.escenario.CuerpoPolicia;
import modelo.estadisticas.InfoRatero;
import modelo.personajes.Policia;
import modelo.personajes.Ratero;
import modelo.personajes.Rico;

public class RateroHilosTest {
    ExecutorService executor;
    Ratero ratero;
    Rico rico;
    Policia policia;

    @Test
    public void testRateroHilos() throws InterruptedException, ExecutionException {
        executor = Executors.newFixedThreadPool(2);
        ArrayList<Rico> ricos = new ArrayList<>();
        ArrayList<Policia> policias = new ArrayList<>();
        ricos.add(new Rico());
        policias.add(new Policia());
        int botinSuficiente = 100;
        ratero = new Ratero(Barrio.whiteChapel.getPosicionCentral(), botinSuficiente, botinSuficiente, ricos, policias);
        Future<InfoRatero> submit = executor.submit(ratero);
        assertEquals(botinSuficiente, submit.get().getTotalRobado());
        executor.shutdown();
    }

    @Test
    public void testRateroHilosDos() throws InterruptedException, ExecutionException {
        executor = Executors.newFixedThreadPool(1);
        ArrayList<Rico> ricos = new ArrayList<>();
        ArrayList<Policia> policias = new ArrayList<>();
        ricos.add(new Rico());
        policias.add(new Policia());
        int botinSuficiente = 200;
        ratero = new Ratero(Barrio.whiteChapel.getPosicionCentral(), botinSuficiente, botinSuficiente, ricos, policias);
        Future<InfoRatero> submit = executor.submit(ratero);
        assertNotEquals(botinSuficiente, submit.get().getTotalRobado());
        executor.shutdown();
    }

    @Test
    public void testRateroHilosTres() throws InterruptedException, ExecutionException {
        //solo dos hilos
        executor = Executors.newCachedThreadPool();
        ArrayList<Rico> ricos = new ArrayList<>();
        ArrayList<Policia> policias = new ArrayList<>();
        ricos.add(new Rico());
        ricos.add(new Rico());
        policias.add(new Policia());
        int botinSuficiente = 200;
        ratero = new Ratero(Barrio.whiteChapel.getPosicionCentral(), botinSuficiente, botinSuficiente, ricos, policias);
        Future<InfoRatero> submit = executor.submit(ratero);
        assertEquals(botinSuficiente, submit.get().getTotalRobado());
        executor.shutdown();
    }

    @Test
    public void testRateroHilosCuatro() throws InterruptedException, ExecutionException {
        executor = Executors.newCachedThreadPool();
        ArrayList<Rico> ricos = new ArrayList<>();
        ArrayList<Policia> policias = new ArrayList<>();
        CuerpoPolicia cuerpoPolicia = new CuerpoPolicia();
        Rico e = new Rico();
        ricos.add(e);
        Policia e2 = new Policia(ricos);
        policias.add(e2);
        cuerpoPolicia.setCuerpoPolicia(ricos, policias);
        int botinSuficiente = 100;
        ratero = new Ratero(Barrio.whiteChapel.getPosicionCentral(), botinSuficiente, botinSuficiente, ricos, policias);
        Future<Integer> submit2 = executor.submit(e);
        executor.execute(e2);
        Future<InfoRatero> submit = executor.submit(ratero);
        InfoRatero infoRatero = submit.get();
        if (ratero.isEnCarcel()) {
            showMessage("RAteroHilosTest:testRateroHilosTres","Ratero encarcelado", true);
            assertEquals(0, infoRatero.getTotalRobado());
        } else {
            showMessage("RAteroHilosTest:testRateroHilosTres","Ratero no encarcelado", true);
            assertEquals(botinSuficiente, infoRatero.getTotalRobado());
        }
        executor.shutdown();
    }

}
