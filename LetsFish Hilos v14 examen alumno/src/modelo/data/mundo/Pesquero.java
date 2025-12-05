// Interface Pesquero
package modelo.data.mundo;


import java.util.List;

import modelo.data.mar.Cardumen;

interface Pesquero {
    void decidirCardumen(List<Cardumen> cardumenes);

    void moverse();

    void pescar() throws InterruptedException;

    void regresarAPuerto();
}