package comienzo01;


class Meollo extends Thread{
        Meollo(String str){
           //Lo pruebo asi y luego lo pongo
           super(str);
        }       
    }
public class Comienzo2 {
    
    public static void main(String[] args) {
        Meollo joe=new Meollo("pepe");
        System.out.println(joe.getName());
        joe.start();
    }
}
