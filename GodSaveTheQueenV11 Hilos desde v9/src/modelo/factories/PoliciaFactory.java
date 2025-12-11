package modelo.factories;

import modelo.escenario.Posicion;
import modelo.personajes.Policia;

public class PoliciaFactory extends PersonajeFactory<Policia> {
    private Posicion posicion;
    private int radioAccion;

    public PoliciaFactory(Posicion posicion, int radioAccion) {
        this.posicion = posicion;
        this.radioAccion = radioAccion;
    }

    @Override
    public Policia crear() {
        Policia policia = new Policia();
        configurar(policia);
        return policia;
    }

    @Override
    protected void configurar(Policia policia) {
        policia.setPosicion(posicion);
        policia.setRadioAccion(radioAccion);
    }
}
