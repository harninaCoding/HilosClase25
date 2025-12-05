// Interface Pesquero
package modelo.data;


import java.util.List;
import java.util.Optional;

interface Pesquero {
    void decidirCardumen(List<Cardumen> cardumenes);

    void moverse();

    void pescar() throws InterruptedException;

    void regresarAPuerto();
}