package modelo.data.mar;

import java.util.List;

import modelo.data.mundo.Barco;
import modelo.data.mundo.Puerto;
import modelo.enums.Dimensiones;
import modelo.oms.BarcosOM;
import modelo.oms.CardumenOM;
import modelo.oms.PuertosOM;

public class Mundo {
	Mar mar;
	List<Puerto> puertos;
	List<Cardumen> cardumenes;
	List<Barco> barcos;
	ServicioSatelite servicioSatelite;

	public Mundo() {
		super();
		mar = new Mar(Dimensiones.small.getDimension());
		int cantidadPuertos=5;
		puertos = PuertosOM.getAll(mar.getDimension(),cantidadPuertos);
		int cantidadCardumenes=2;
		cardumenes = CardumenOM.getAll(mar.getDimension(),cantidadCardumenes);
		mar.setCardumenes(cardumenes);
		int cantidadBarcos=20;
		barcos=BarcosOM.getAll(mar,puertos,servicioSatelite,cantidadBarcos);
		servicioSatelite=new ServicioSatelite(mar);
	}

	private void createDiaPesca() {
		//arrancar los cardumenes
		//arrancar los barcos
		//si todos los barcos vuelven a puerto entonces pasamos al siguiente dia
	}
	
	public Mar getMar() {
		return mar;
	}

	public List<Puerto> getPuertos() {
		return puertos;
	}

	public List<Cardumen> getCardumenes() {
		return cardumenes;
	}

	public List<Barco> getBarcos() {
		return barcos;
	}

	public ServicioSatelite getServicioSatelite() {
		return servicioSatelite;
	}

}
