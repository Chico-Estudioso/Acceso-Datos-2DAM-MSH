package FicherosBinarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Practica2 {
	public static void main(String[] args) {
		String ruta = "src/FicherosBinarios/datosBinarios.dat";

		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta))) {
			double numeroDecimal = 25.75;
			boolean valorBooleano = true;
			String texto = "Hola colega";

			dos.writeDouble(numeroDecimal);
			dos.writeBoolean(valorBooleano);
			dos.writeUTF(texto); // writeUTF guarda el texto con longitud + contenido

			System.out.println("Datos binarios escritos correctamente");
			System.out.println("Ubicación del fichero: " + new File(ruta).getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Error al escribir el fichero: " + e.getMessage());
		}

		try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
			double numeroLeido = dis.readDouble();
			boolean booleanoLeido = dis.readBoolean();
			String textoLeido = dis.readUTF();

			System.out.println("\n--- Datos leídos del fichero ---");
			System.out.println("Número decimal: " + numeroLeido);
			System.out.println("Booleano: " + booleanoLeido);
			System.out.println("Texto: " + textoLeido);

		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
		}
	}
}
