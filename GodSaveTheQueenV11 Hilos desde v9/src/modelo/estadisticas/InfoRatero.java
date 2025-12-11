package modelo.estadisticas;

import java.util.ArrayList;

import modelo.personajes.Rico;

public class InfoRatero {
    private final int totalRobado;
    private final ArrayList<Rico> ricosRobados;

    public InfoRatero(int totalRobado, ArrayList<Rico> ricosRobados) {
        this.totalRobado = totalRobado;
        this.ricosRobados = ricosRobados;
    }

    public int getTotalRobado() {
        return totalRobado;
    }

    public ArrayList<Rico> getRicosRobados() {
        return ricosRobados;
    }

}
