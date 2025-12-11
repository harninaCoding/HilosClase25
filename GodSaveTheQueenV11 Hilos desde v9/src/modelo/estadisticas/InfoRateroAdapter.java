package modelo.estadisticas;

import java.util.ArrayList;

import modelo.personajes.Rico;

public interface InfoRateroAdapter {

    public default InfoRatero map(int botin,ArrayList<Rico> ricosRobados) {
        return new InfoRatero(botin, ricosRobados);
    }
}
