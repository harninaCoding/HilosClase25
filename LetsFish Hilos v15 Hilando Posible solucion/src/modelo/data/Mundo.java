package modelo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import modelo.enums.Dimensiones;
import modelo.oms.BarcosOM;
import modelo.oms.CardumenOM;
import modelo.oms.PuertosOM;

public class Mundo implements Runnable {
	Mar mar;
	List<Puerto> puertos;
	List<Cardumen> cardumenes;
	List<Barco> barcos;
	ServicioSatelite servicioSatelite;
	ExecutorService executorService;

	public Mundo() {
		super();
		mar = new Mar(Dimensiones.small.getDimension());
		int cantidadPuertos = 5;
		puertos = PuertosOM.getAll(mar.getDimension(), cantidadPuertos);
		int cantidadCardumenes = 2;
		cardumenes = CardumenOM.getAll(mar.getDimension(), cantidadCardumenes);
		mar.setCardumenes(cardumenes);
		int cantidadBarcos = 3;
		servicioSatelite = new ServicioSatelite(mar);
		barcos = BarcosOM.getAll(mar, puertos, servicioSatelite, cantidadBarcos);
		executorService = Executors.newCachedThreadPool();
		cardumenes.forEach(executorService::execute);
	}

	@Override
	public void run() {
		int dias = 5;
		List<Future<InfoPesca>> infoPescas;
		int infopescastotales = 0;
		int barcostotales = 0;
		for (int i = 0; i < dias; i++) {
			infoPescas = new ArrayList();
			for (Barco barco : barcos) {
				Future<InfoPesca> submit = executorService.submit(barco);
				infoPescas.add(submit);
				barcostotales++;
			}
			for (Future<InfoPesca> future : infoPescas) {
				try {
					InfoPesca infoPesca = future.get();
					Puerto puertoBase = infoPesca.getBarco().getPuertoBase();
					puertoBase.add(infoPesca);
					infopescastotales++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			}
			Mar.dia += 1;
		}
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
