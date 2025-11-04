package comienzo01;


public class HilandRuneando implements Runnable{

    public void run() {
        for (int i = 0; i < 5 ; i++)
            System.out.println(i + " " +
                    Thread.currentThread().getName());
        System.out.println("Termina thread " +
                Thread.currentThread().getName());
    }
    public static void main (String [] args) {
        new Thread (new HilandRuneando(), "Pepe").start();
        new Thread (new HilandRuneando(), "Juan").start();
        System.out.println("Termina thread main");
    }
}

