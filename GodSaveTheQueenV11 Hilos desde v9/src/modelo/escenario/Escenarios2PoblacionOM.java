package modelo.escenario;

import modelo.OM.PoblacionOM;

public class Escenarios2PoblacionOM implements Mapper<CuerpoPolicia, Escenarios, PoblacionOM> {
    @Override
    public PoblacionOM map(CuerpoPolicia cuerpoPolicia, Escenarios escenarios) {
        return new PoblacionOM(
                cuerpoPolicia,
                escenarios.getCantidadRicos(),
                escenarios.getBienRicos(),
                escenarios.getCantidadPolicias(),
                escenarios.getRadioPolicias(),
                escenarios.getCantidadRateros(),
                escenarios.getRadioRateros(),
                escenarios.getBotinSuficiente());
    }
}