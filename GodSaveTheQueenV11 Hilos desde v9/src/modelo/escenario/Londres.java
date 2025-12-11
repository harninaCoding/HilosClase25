package modelo.escenario;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import modelo.OM.PoblacionOM;
import modelo.estadisticas.InfoRatero;
import modelo.personajes.Policia;
import modelo.personajes.Ratero;
import modelo.personajes.Rico;

public class Londres {
	CuerpoPolicia cuerpoPolicia = new CuerpoPolicia();
	PoblacionOM poblacion;
	List<Rico> ricos;
	List<Policia> policias;
	List<Ratero> rateros;
	List<Rico> historiaRicos;
	List<Ratero> historiaRateros;
	List<InfoRatero> infoRateros;

	public Londres(Escenarios escenario) {
		super();
		infoRateros = new ArrayList<InfoRatero>();
		poblacion = createPoblacionOM(escenario);
		policias = poblacion.getPolicias();
		ricos = poblacion.getRicos();
		rateros = poblacion.getRateros();
		historiaRicos = new ArrayList<Rico>();
		historiaRicos.addAll(ricos);
		historiaRateros = new ArrayList<Ratero>();
		historiaRateros.addAll(rateros);
		desarrollarLondres();
	}

	private PoblacionOM createPoblacionOM(Escenarios escenario) {
		return new Escenarios2PoblacionOM().map(cuerpoPolicia, escenario);
	}

	public List<Rico> getHistoriaRicos() {
		return historiaRicos;
	}

	public List<Ratero> getHistoriaRateros() {
		return historiaRateros;
	}

	public void desarrollarLondres() {
		int numHilos = ricos.size() + rateros.size() + policias.size();
		ExecutorService executor = Executors.newFixedThreadPool(numHilos);

		List<Future<Integer>> futuresRicos = new ArrayList<>();
		List<Future<InfoRatero>> futuresRateros = new ArrayList<>();
		// List<Future<Integer>> futuresPolicias = new ArrayList<>();

		try {
			// Iniciar hilos para todos los Ricos
			for (Rico rico : ricos) {
				Future<Integer> future = executor.submit(rico);
				futuresRicos.add(future);
			}

			// Iniciar hilos para todos los Rateros
			for (Ratero ratero : rateros) {
				Future<InfoRatero> future = executor.submit(ratero);
				futuresRateros.add(future);
			}

			// Iniciar hilos para todos los Policías
			for (Policia policia : policias) {
				executor.execute(policia);
			}

			// Esperar a que todos los hilos terminen y recoger resultados
			int totalPerdido = 0;
			for (Future<Integer> future : futuresRicos) {
				totalPerdido += future.get();
			}

			int totalRobado = 0;
			for (Future<InfoRatero> future : futuresRateros) {
				totalRobado += future.get().getTotalRobado();
			}

			for (Future<InfoRatero> future : futuresRateros) {
				infoRateros.add(future.get());
			}
			// Validación final
			System.out.println("\n=== FIN DEL DÍA EN LONDRES ===");
			System.out.println("Total dinero perdido por ricos: " + totalPerdido);
			System.out.println("Total dinero robado por rateros: " + totalRobado);

			if (totalRobado == totalPerdido) {
				System.out.println("✓ Validación exitosa: El dinero robado coincide con el dinero perdido");
			} else {
				System.err.println("✗ ERROR: Dinero robado (" + totalRobado +
						") NO coincide con dinero perdido (" + totalPerdido + ")");
			}

		} catch (Exception e) {
			System.err.println("Error durante la simulación: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerrar el ExecutorService
			executor.shutdown();
		}
	}

	public List<InfoRatero> getInfoRateros() {
		return infoRateros;
	}
}
