package FicherosBinarios;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Practica1 {
	public static void main(String[] args) {
		// Abrimos el fichero binario para escritura usando try-with-resources
		try (DataOutputStream dos = new DataOutputStream(
				new FileOutputStream("src/FicherosBinarios/ficheroBinario.dat"))) {

			// Escribimos bytes (valores 97, 98, 99, 100, 101)
			dos.writeByte(97);
			dos.writeByte(98);
			dos.writeByte(99);
			dos.writeByte(100);
			dos.writeByte(101);

			System.out.println("Fichero binario creado correctamente");

		} catch (IOException e) {
			System.out.println("Error al escribir el fichero: " + e.getMessage());
		}
	}
}
