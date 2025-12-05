package modelo.oms;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import modelo.data.Coordenada;
import modelo.data.Puerto;

public class PuertosOM {

    /**
     * Genera una lista de puertos con posiciones calculadas dentro de los límites del mundo.
     *
     * @param dimension Dimensiones del mundo donde se ubicarán los puertos.
     * @param cantidadPuertos Número de puertos a generar.
     * @return Lista de puertos generados según los datos proporcionados.
     */
    public static List<Puerto> getAll(Dimension dimension, int cantidadPuertos) {
        List<Puerto> puertos = new ArrayList<>();

        // Datos predefinidos para las pruebas
        String[] nombres = { "Siracusa", "Palermo", "Catania", "Messina", "Agrigento" };

        for (int i = 0; i < cantidadPuertos; i++) {
            int index = i % nombres.length; // Rotar entre los nombres predefinidos
            // Calcular posición determinística dentro de los límites del mundo
            int x = (i + 1) * (dimension.width / (cantidadPuertos + 1));
            int y = (i + 1) * (dimension.height / (cantidadPuertos + 1));
            Coordenada posicion = new Coordenada(x, y);

            Puerto puerto = new Puerto(
                (long) (i + 1), // ID único para cada puerto
                nombres[index],
                posicion
            );
            puertos.add(puerto);
        }

        return puertos;
    }
}
