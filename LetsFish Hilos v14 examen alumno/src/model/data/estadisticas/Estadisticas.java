package model.data.estadisticas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.data.mundo.Barco;
import modelo.data.mundo.InfoPesca;
import modelo.enums.EspeciePez;
import modelo.enums.TipoBarco;

public class Estadisticas {
	private List<InfoPesca> infoPescas;

	public Estadisticas(List<InfoPesca> infoPescas) {
		super();
		this.infoPescas = infoPescas;
	}

	// java 8
	/**
	 * Obtiene la cantidad pescada de un tipo de pez en todas las pescas
	 * 
	 * @param especiePez
	 * @return
	 */
	public Long getCantidadEspecie(EspeciePez especiePez) {
		Long retorno = 0l;
		for (InfoPesca infoPesca : infoPescas) {
			if (infoPesca.getEspeciePez().equals(especiePez)) {
				retorno += infoPesca.getCantidad();
			}
		}
		return retorno;
	}

	/*
	 * Obtiene un mapa donde se expresa el total por especie
	 */
	public Map<TipoBarco, Long> getCantidadPorTipo() {
		HashMap<TipoBarco, Long> mapa = new HashMap();
		for (TipoBarco tipoBarco : TipoBarco.values()) {
			long amount = 0l;
			for (InfoPesca infoPesca : infoPescas) {
				if (infoPesca.getBarco().getTipo().equals(tipoBarco))
					amount += infoPesca.getCantidad();
			}
			mapa.put(tipoBarco, amount);
		}
		return mapa;
	}

	/**
	 * Retorna la Especie de pez mas pescada por un barco posible que no haya
	 * pescado nada, nunca
	 * 
	 * @param barco
	 * @return
	 */
	public EspeciePez getEspeciePezUnBarco(Barco barco) {
		HashMap<EspeciePez, Long> pescas = new HashMap();
		for (EspeciePez especiePez : EspeciePez.values()) {
			long amount = 0;
			for (InfoPesca infoPesca : infoPescas) {
				if (infoPesca.getBarco().equals(barco)) {
					if (infoPesca.getEspeciePez().equals(especiePez)) {
						amount += infoPesca.getCantidad();
					}
				}
			}
			if (amount > 0) {
				pescas.put(especiePez, amount);
			}
			if (pescas.size() > 0) {
				EspeciePez especieMasPescada = null;
				long maxCantidad = 0;
				for (Map.Entry<EspeciePez, Long> entry : pescas.entrySet()) {
					if (entry.getValue() > maxCantidad) {
						maxCantidad = entry.getValue();
						especieMasPescada = entry.getKey();
					}
				}
				return especieMasPescada;
			}

		}
		return null;

	}

	public Map<EspeciePez, Double> getMediaPescaPorEspecie() {
		HashMap<EspeciePez, Double> pescas = new HashMap();
		int diaFinal = 0;
		for (InfoPesca infoPesca : infoPescas) {
			if (infoPesca.getDia() > diaFinal)
				diaFinal = infoPesca.getDia();
		}
		for (EspeciePez especiePez : EspeciePez.values()) {
			long amount = 0;
			for (InfoPesca infoPesca : infoPescas) {
				if(infoPesca.getEspeciePez().equals(especiePez))
				amount += infoPesca.getCantidad();
			}
			pescas.put(especiePez, ((double) amount) / diaFinal);
		}
		return pescas;
	}
}
