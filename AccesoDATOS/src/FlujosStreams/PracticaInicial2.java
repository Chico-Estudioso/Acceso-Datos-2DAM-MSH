package FlujosStreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PracticaInicial2 {
	public static void main(String[] args) {
		// Declaramos el stream de lectura
		BufferedReader fichero = null;
		// Si el fichero no existe se lanza una excepción que hay que capturar
		try {
			// Abrimos el fichero
			fichero = new BufferedReader(
					new FileReader("/home/diurno/eclipse-workspace/AccesoDatos_T1/src/FlujosStreams/nombre.txt"));
			// Variable para ir leyendo los caracteres del fichero
			String linea;
			// Bucledelecturadelfichero
			System.out.println("Contenido del fichero :");
			while ((linea = fichero.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (Exception e) {
			System.out.println("Error al abrir el fichero");
			e.getMessage();
			e.printStackTrace();
		} finally {
			try {
				fichero.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
