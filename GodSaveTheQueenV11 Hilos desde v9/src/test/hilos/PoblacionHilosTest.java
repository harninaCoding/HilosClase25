package test.hilos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import modelo.escenario.Escenarios;
import modelo.escenario.Londres;
import modelo.estadisticas.InfoRatero;

public class PoblacionHilosTest {
	Londres londres;

	@Test
	public void testPoblacion() {
		londres = new Londres(Escenarios.Unos);
		londres.desarrollarLondres();
		List<InfoRatero> infoRateros = londres.getInfoRateros();
		assertTrue(isNoRicoRepetido(infoRateros));
	}
	@Test
	public void testPoblacion2() {
		londres = new Londres(Escenarios.Unos);
		londres.desarrollarLondres();
		List<InfoRatero> infoRateros = londres.getInfoRateros();
		assertTrue(isNoRicoRepetido2(infoRateros));
	}

	// muy lento true si no hay ricos repetidos
	private boolean isNoRicoRepetido(List<InfoRatero> infoRateros) {
		for (int i = 0; i < infoRateros.size() - 1; i++) {
			for (int j = i + 1; j < infoRateros.size(); j++) {
				// true si no comparten elementos
				if (!Collections.disjoint(infoRateros.get(i).getRicosRobados(), infoRateros.get(j).getRicosRobados())) {
					return false;
				}
			}
		}
		return true;
	}
	//mas rapido
	private boolean isNoRicoRepetido2(List<InfoRatero> infoRateros) {
		assertEquals(Escenarios.Unos.getCantidadRicos(),londres.getHistoriaRicos().size());
		return infoRateros.stream().map(InfoRatero::getRicosRobados).flatMap(List::stream).distinct().count() == infoRateros.size();
	}

}