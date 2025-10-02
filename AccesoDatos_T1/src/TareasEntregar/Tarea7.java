package TareasEntregar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tarea7 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<String> lista = new ArrayList<String>();
		System.out.println("Introduce nombres de fichero (escribe 'salir' para terminar):");
		int contador = 0;
		while (true) {
			System.out.print("Dame un nombre de fichero: ");
			String nombre = sc.nextLine();
			// File fNombre = new File(nombre);
			if (nombre.equalsIgnoreCase("salir"))
				break;
			lista.add(nombre);
			contador++;
			// contador=lista.size();
			System.out.println("Fichero añadido número: " + contador);
		}
		for (String nombre : lista) {
			File f7 = new File(nombre);
			System.out.println("------------------------------------------------");
            System.out.println(nombre);
            System.out.println("------------------------------------------------");
            if (f7.exists()) {
				if (f7.isFile()) {
					System.out.println("Es un archivo");
				} else {
					System.out.println("Es un directorio");
				}
			System.out.print("El nombre de su ruta absoluta es: "+f7.getAbsolutePath());
			String permisos = "";
			permisos += f7.canRead() ? "r" : "-";
			permisos += f7.canWrite() ? "w" : "-";
			permisos += f7.canExecute() ? "x" : "-";
			System.out.println(permisos);
			System.out.println("El tamaño del archivo es: "+f7.length());
			
			} else {
				System.out.println("El Archivo no existe");
			}
		}
	}
}
