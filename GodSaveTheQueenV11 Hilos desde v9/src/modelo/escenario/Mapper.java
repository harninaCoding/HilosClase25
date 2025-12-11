package modelo.escenario;

public interface Mapper<X, S, T> {
    public T map(X x, S s);
}
