package test.hilos;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.personajes.Ratero;
import modelo.personajes.Rico;

public class RicoHilosTest {
    Rico rico;
    ExecutorService executor;

    @BeforeEach
    void setUp() {
        rico = new Rico();
        executor = Executors.newCachedThreadPool();
    }

    @Test
    void testAtracando() throws InterruptedException {
        Ratero ratero = new Ratero(null, 0, 0, null, null);
        Future<Integer> submit = executor.submit(rico);
        Thread.sleep(100);
        rico.atracando(ratero);
        try {
            Integer integer = submit.get();
            // SI le han robado dinero y retorna 100
            assertTrue(integer == 100);
        } catch (InterruptedException | ExecutionException e) {
            fail();
        }
        assertTrue(!rico.isActivo());
    }

    @Test
    void testRicoPaseaSinAtracar() {
        Future<Integer> submit = executor.submit(rico);
        try {
            Integer integer = submit.get();
            // no le han robado dinero y retorna 0
            assertTrue(integer == 0);
        } catch (InterruptedException | ExecutionException e) {
            fail();
        }
    }
}
