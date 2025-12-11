package modelo.factories;

public abstract class PersonajeFactory<T> {

	public abstract T crear();

	protected abstract void configurar(T personaje);
}
