package modelo.factories;

import modelo.escenario.CuerpoPolicia;
import modelo.escenario.Posicion;
import modelo.personajes.Rico;

public class RicoFactory extends PersonajeFactory<Rico> {
    private Posicion posicion;
    private CuerpoPolicia cuerpoPolicia;
    private int bien;

    public RicoFactory(Posicion posicion, CuerpoPolicia cuerpoPolicia, int bien) {
        this.posicion = posicion;
        this.cuerpoPolicia = cuerpoPolicia;
        this.bien = bien;
    }

    @Override
    public Rico crear() {
        Rico rico = new Rico();
        configurar(rico);
        return rico;
    }

    @Override
    protected void configurar(Rico rico) {
        rico.addPropertyChangeListener(cuerpoPolicia);
        rico.setPosicion(posicion);
        rico.setBien(bien);
    }
}
