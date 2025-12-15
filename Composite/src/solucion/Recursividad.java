package solucion;
import java.io.File;
import java.io.IOException;

public class Recursividad {
	public static void main(String[] args) {
		File file=new File("c:\\DAM2019");
		recorrer(file);
	}

	private static void recorrer(File f) {
		System.out.println(f.isDirectory());
		File[] listFiles = f.listFiles();
		for (File file : listFiles) {
			//camino recursivo
			if(file.isDirectory()) {
				recorrer(file);
			}
			//salida recursiva
			else {
				System.out.println(file.getPath());
			}
		}
	}
}
