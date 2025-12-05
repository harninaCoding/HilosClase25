package test.modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.data.estadisticas.Estadisticas;
import modelo.data.mundo.Barco;
import modelo.data.mundo.InfoPesca;
import modelo.enums.EspeciePez;
import modelo.enums.TipoBarco;

class EstadisticasTest {
	  private Estadisticas estadisticas;
	    private List<Barco> barcos;
	    private List<InfoPesca> infoPescas;

	    @BeforeEach
	    void before() {
	        // Crear barcos predefinidos
	        barcos = new ArrayList<>();
	        barcos.add(new Barco("Barco A", null, null, 100, 10, null, TipoBarco.CERCO, null));
	        barcos.add(new Barco("Barco B", null, null, 200, 15, null, TipoBarco.PALANGRE, null));

	        // Crear datos de pesca
	        infoPescas = new ArrayList<>();
	        infoPescas.add(new InfoPesca(EspeciePez.SARDINA, 50, barcos.get(0), 1));
	        infoPescas.add(new InfoPesca(EspeciePez.MERLUZA, 30, barcos.get(0), 1));
	        infoPescas.add(new InfoPesca(EspeciePez.ATUN, 100, barcos.get(1), 1));
	        infoPescas.add(new InfoPesca(EspeciePez.PEZ_ESPADA, 200, barcos.get(1), 1));

	        // Inicializar Estadisticas con el constructor
	        estadisticas = new Estadisticas(infoPescas);
	    }

	    @Test
	    void testGetCantidadEspecie() {
	        // Cantidad total de sardinas
	        assertEquals(50L, estadisticas.getCantidadEspecie(EspeciePez.SARDINA));

	        // Cantidad total de merluzas
	        assertEquals(30L, estadisticas.getCantidadEspecie(EspeciePez.MERLUZA));

	        // Cantidad total de atunes
	        assertEquals(100L, estadisticas.getCantidadEspecie(EspeciePez.ATUN));

	        // Cantidad de una especie no pescada
	        assertEquals(0L, estadisticas.getCantidadEspecie(EspeciePez.BACALAO));
	    }

	    @Test
	    void testGetCantidadPorTipo() {
	        Map<TipoBarco, Long> cantidadPorTipo = estadisticas.getCantidadPorTipo();

	        // Verificar las cantidades por tipo de barco
	        assertEquals(80L, cantidadPorTipo.get(TipoBarco.CERCO)); // Sardina + Merluza
	        assertEquals(300L, cantidadPorTipo.get(TipoBarco.PALANGRE)); // Atún + Pez Espada
	    }

	    @Test
	    void testGetEspeciePezUnBarco() {
	        // Especie más pescada por Barco A
	        assertEquals(EspeciePez.SARDINA, estadisticas.getEspeciePezUnBarco(barcos.get(0)));

	        // Especie más pescada por Barco B
	        assertEquals(EspeciePez.ATUN, estadisticas.getEspeciePezUnBarco(barcos.get(1)));

	        // Barco sin datos de pesca
	        Barco barcoSinPesca = new Barco("Barco C", null, null, 150, 20, null, TipoBarco.CERCO, null);
	        assertNull(estadisticas.getEspeciePezUnBarco(barcoSinPesca));
	    }

	    @Test
	    void testGetMediaPescaPorEspecie() {
	        Map<EspeciePez, Double> mediaPescaPorEspecie = estadisticas.getMediaPescaPorEspecie();

	        // Verificar medias
	        assertEquals(50.0, mediaPescaPorEspecie.get(EspeciePez.SARDINA));
	        assertEquals(30.0, mediaPescaPorEspecie.get(EspeciePez.MERLUZA));
	        assertEquals(100.0, mediaPescaPorEspecie.get(EspeciePez.ATUN));
	        assertEquals(200.0, mediaPescaPorEspecie.get(EspeciePez.PEZ_ESPADA));
	    }

}
