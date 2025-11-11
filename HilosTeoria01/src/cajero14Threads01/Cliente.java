package cajero14Threads01;


//Empezamos suponiendo que el cliente solo va  hacer una cosa, pasar el carro
public class Cliente {

	private String nombre;
	//lo que dura pasar cada producto sobre el carro
	private int[] carroCompra;

	public Cliente() {
	}

	public Cliente(String nombre, int[] carroCompra) {
		this.nombre = nombre;
		this.carroCompra = carroCompra;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int[] getCarroCompra() {
		return carroCompra;
	}

	public void setCarroCompra(int[] carroCompra) {
		this.carroCompra = carroCompra;
	}

}
