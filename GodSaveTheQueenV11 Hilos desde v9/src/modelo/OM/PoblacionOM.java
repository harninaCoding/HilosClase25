package modelo.OM;

import java.util.ArrayList;
import java.util.List;

import modelo.escenario.Barrio;
import modelo.escenario.CuerpoPolicia;
import modelo.factories.PoliciaFactory;
import modelo.factories.RateroFactory;
import modelo.factories.RicoFactory;
import modelo.personajes.Policia;
import modelo.personajes.Ratero;
import modelo.personajes.Rico;

public class PoblacionOM {
	private CuerpoPolicia cuerpoPolicia;
	private List<Rico> ricos;
	private List<Policia> policias;
	private List<Ratero> rateros;

	public PoblacionOM(CuerpoPolicia cuerpoPolicia,
		int cantidadRicos,
		int bienRicos,
		int cantidadPolicias,
		int radioPolicias,
		int cantidadRateros,
		int radioRateros,
		int botinSuficiente) {
		super();
		this.cuerpoPolicia = cuerpoPolicia;
		ricos = createRicos(cuerpoPolicia, cantidadRicos,bienRicos);
		policias = createPolicias(cantidadPolicias,radioPolicias);
		this.cuerpoPolicia.setCuerpoPolicia(ricos, policias);
		rateros = createRateros(cantidadRateros,radioRateros,botinSuficiente);
	}

	public List<Policia> createPolicias(int cantidad,int radio) {
		ArrayList<Policia> policias = new ArrayList<>();
		for (int i = 0; i < cantidad; i++)
			policias.add(new PoliciaFactory(Barrio.Kensington.getPosicionAleatoriaEnBarrio(), radio).crear());
		return policias;
	}

	public List<Rico> createRicos(CuerpoPolicia cuerpoPolicia, int cantidad,int bien) {
		ArrayList<Rico> ricos = new ArrayList<>();
		for (int i = 0; i < cantidad; i++)
			ricos.add(new RicoFactory(Barrio.Kensington.getPosicionAleatoriaEnBarrio(), cuerpoPolicia, bien).crear());
		return ricos;
	}

	public List<Ratero> createRateros(int cantidad,int radio,int botinSuficiente) {
		ArrayList<Ratero> rateros = new ArrayList<>();
		for (int i = 0; i < cantidad; i++)
			rateros.add(new RateroFactory(Barrio.whiteChapel.getPosicionAleatoriaEnBarrio(), radio, botinSuficiente, ricos, policias)
					.crear());
		return rateros;
	}

	public List<Rico> getRicos() {
		return ricos;
	}

	public List<Policia> getPolicias() {
		return policias;
	}

	public List<Ratero> getRateros() {
		return rateros;
	}
}
