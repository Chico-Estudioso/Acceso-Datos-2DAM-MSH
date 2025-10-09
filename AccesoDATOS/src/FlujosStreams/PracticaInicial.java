package FlujosStreams;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PracticaInicial {
	public static void main(String[] args) {
		FileReader fichero = null;
		try {
			// Abrimos el fichero para lectura
			fichero = new FileReader("/home/diurno/eclipse-workspace/AccesoDatos_T1/src/FlujosStreams/nombre.txt");
			// Variable para ir leyendo los caracteres del fichero
			char letra;
			int caracter;
			// Recorremos para mostrar el contenido
			while ((caracter = fichero.read()) != -1) {
				// mostramos el carácter leído
				letra = (char) caracter;
				System.out.print(letra);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer");
			e.printStackTrace();
		} finally {
			// Cerramos el fichero
			try {
				fichero.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
