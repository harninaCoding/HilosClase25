package modelo.personajes.states;

import modelo.personajes.EstadoRatero;
import modelo.personajes.Ratero;

/**
 * Interfaz para el patrón State del Ratero
 */
public interface EstadoRateroState {
    /**
     * Ejecuta la acción correspondiente a este estado
     * 
     * @param ratero El ratero que ejecuta la acción
     */
    void ejecutarAccion(Ratero ratero);

    /**
     * Retorna el tipo de estado para compatibilidad con código existente
     * 
     * @return El tipo de estado del enum EstadoRatero
     */
    EstadoRatero getTipo();
}
