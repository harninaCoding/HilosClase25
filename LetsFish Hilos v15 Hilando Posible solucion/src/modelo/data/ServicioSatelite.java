// Servicio Satélite
package modelo.data;


import java.util.List;

public class ServicioSatelite {
    private Mar mar;

    public  ServicioSatelite(Mar mar) {
        this.mar = mar;
    }

    public List<Cardumen> obtenerInformeDiario() {
        return mar.obtenerCardumenes();
    }

	public Coordenada getPosicion(Cardumen cardumen) {
		return cardumen.getPosicion();
	}
	
}
