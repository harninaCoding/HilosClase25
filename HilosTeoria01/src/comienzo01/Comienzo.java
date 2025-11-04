package comienzo01;

public class Comienzo {
    public static void main(String[] args) {
        //cuando se crea un hilo se le puede poner un nombre
        //Entonces podemos hacer referencia a el por ese nombre
        Thread hilo=new Thread("glen");
        System.out.println(hilo.getName());
        //Con esto ya has creado un hilo aunque vale pa poco
        //ejecutarlo
        hilo.start();
       }
    
}
