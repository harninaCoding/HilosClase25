package modelo.escenario;

public enum Escenarios {
    Unos(1, 1, 1, 100, 10, 50, 100),
    masRicosQueRateros(2, 2, 5, 100, 10, 50, 100),
    masRaterosQueRicos(2, 5, 2, 100, 10, 50, 100);

    private int cantidadPolicias;
    private int cantidadRateros;
    private int cantidadRicos;
    private int bienRicos;
    private int radioPolicias;
    private int radioRateros;
    private int botinSuficiente = 100;

    Escenarios(int cantidadPolicias, int cantidadRateros, int cantidadRicos, int bienRicos, int radioPolicias,
            int radioRateros, int botinSuficiente) {
        this.cantidadPolicias = cantidadPolicias;
        this.cantidadRateros = cantidadRateros;
        this.cantidadRicos = cantidadRicos;
        this.bienRicos = bienRicos;
        this.radioPolicias = radioPolicias;
        this.radioRateros = radioRateros;
        this.botinSuficiente = botinSuficiente;
    }

    public int getCantidadPolicias() {
        return cantidadPolicias;
    }

    public int getCantidadRateros() {
        return cantidadRateros;
    }

    public int getCantidadRicos() {
        return cantidadRicos;
    }

    public int getBienRicos() {
        return bienRicos;
    }

    public int getRadioPolicias() {
        return radioPolicias;
    }

    public int getRadioRateros() {
        return radioRateros;
    }

    public int getBotinSuficiente() {
        return botinSuficiente;
    }
}
