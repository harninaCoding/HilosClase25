package ejerciciosincronizacion04.planteamiento;

public class Mundo {
public static void main(String[] args) {
	Fuente fuente=Fuente.getInstance();
	fuente.rellenar();
	Ser humano=new Ser("Felix",2);
	humano.vivir();
	Ser humanoe=new Ser("Manolito",4);
	humanoe.vivir();
}
}
