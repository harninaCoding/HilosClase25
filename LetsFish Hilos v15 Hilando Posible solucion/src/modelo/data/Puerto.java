package modelo.data;

import java.util.ArrayList;
import java.util.List;

public class Puerto {
	private Long id;
	private String nombre;
	private Coordenada sitio;
	private List<InfoPesca> infoPescas;
	
	public Puerto(Long id, String nombre, Coordenada sitio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sitio = sitio;
		this.infoPescas = new ArrayList();
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Coordenada getSitio() {
		return sitio;
	}

	public List<InfoPesca> getInfoPescas() {
		return infoPescas;
	}

	public boolean add(InfoPesca e) {
		return infoPescas.add(e);
	}
	
}
