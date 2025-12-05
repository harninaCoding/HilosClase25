package modelo.oms;

import java.util.ArrayList;
import java.util.List;

import modelo.data.Barco;
import modelo.data.Coordenada;
import modelo.data.Mar;
import modelo.data.Mundo;
import modelo.data.Puerto;
import modelo.data.ServicioSatelite;
import modelo.enums.TipoBarco;

public class BarcosOM {

    /**
     * Genera una lista de barcos con datos predefinidos utilizando información del Mundo.
     *
     * @param cantidadBarcos Número de barcos a generar.
     * @param mundo Instancia de Mundo que contiene los puertos, el servicio satélite y el mar.
     * @return Lista de barcos generados según los datos predefinidos.
     */
	public static List<Barco> getAll(Mar mar, List<Puerto> puertos, ServicioSatelite servicioSatelite,
			int cantidadBarcos) {
        List<Barco> barcos = new ArrayList<>();

        // Datos predefinidos para las pruebas
        String[] nombres = { "Barco 1", "Barco 2", "Barco 3" };
        Coordenada[] posiciones = {
            new Coordenada(0, 0),
            new Coordenada(10, 10),
            new Coordenada(20, 20)
        };
        int[] capacidades = { 100, 200, 300 };
        int[] velocidades = { 5, 10, 15 };
        TipoBarco[] tipos = { TipoBarco.PALANGRE, TipoBarco.CERCO, TipoBarco.PALANGRE };

        // Obtener información de la instancia de Mundo
        for (int i = 0; i < cantidadBarcos; i++) {
            int index = i % nombres.length; // Rotar entre los datos predefinidos
            int puertoIndex = i % puertos.size(); // Rotar entre los puertos disponibles

            Barco barco = new Barco(
                nombres[index],
                posiciones[index],
                puertos.get(puertoIndex),
                capacidades[index],
                velocidades[index],
                servicioSatelite,
                tipos[index],
                mar
            );
            barcos.add(barco);
        }

        return barcos;
    }

	
}

