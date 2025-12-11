package test.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import modelo.escenario.Escenarios;
import modelo.escenario.Londres;
import modelo.personajes.Ratero;
import modelo.personajes.Rico;

public class PoblacionTest {

	@Test
	void test() {
		Londres londres = new Londres(Escenarios.masRicosQueRateros);
		List<Rico> historiaRicos = londres.getHistoriaRicos();
		int bienInicial = historiaRicos.get(0).getBien();
		londres.desarrollarLondres();
		int bienesTotales = historiaRicos.stream().mapToInt(Rico::getBien).sum();
		int totalRobado = bienInicial * historiaRicos.size() - bienesTotales;
		List<Ratero> historiaRateros = londres.getHistoriaRateros();
		int totalRateros = historiaRateros.stream().mapToInt(Ratero::getBotin).sum();
		int totalCarcel = historiaRateros.stream()
				.filter(Ratero::isEnCarcel)
				.mapToInt(Ratero::getBotinConfiscado)
				.sum();
		assertTrue(totalRobado == totalRateros + totalCarcel);
	}

}
