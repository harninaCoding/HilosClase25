package modelo.factories;

import java.util.List;

import modelo.escenario.Posicion;
import modelo.personajes.Policia;
import modelo.personajes.Ratero;
import modelo.personajes.Rico;

public class RateroFactory extends PersonajeFactory<Ratero> {
    private Posicion posicion;
    private int radioAccion;
    private int botinSuficiente;
    private List<Rico> ricos;
    private List<Policia> policias;

    public RateroFactory(Posicion posicion, int radioAccion, int botinSuficiente,
            List<Rico> ricos, List<Policia> policias) {
        this.posicion = posicion;
        this.radioAccion = radioAccion;
        this.botinSuficiente = botinSuficiente;
        this.ricos = ricos;
        this.policias = policias;
    }

    @Override
    public Ratero crear() {
        Ratero ratero = new Ratero(posicion,radioAccion,botinSuficiente,ricos, policias);
        configurar(ratero);
        return ratero;
    }

    @Override
    protected void configurar(Ratero ratero) {
        ratero.setPosicion(posicion);
        ratero.setRadioAccion(radioAccion);
        ratero.setBotinSuficiente(botinSuficiente);
    }
}
