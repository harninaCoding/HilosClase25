package sincroLevelDos16;

public class Cuenta {
	private int saldo;
	private String nombre;

	public Cuenta() {
		this(100, "");
	}

	public Cuenta(int saldo, String nombre) {
		this.saldo = saldo;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public int getSaldo() {
		return saldo;
	}

	public void retiro(int cantidad) {
		saldo -= cantidad;
	}

	public void deposito(int cantidad) {
		saldo += cantidad;
	}
}
