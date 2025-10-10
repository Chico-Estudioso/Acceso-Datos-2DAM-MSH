package TareasEntregar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio17 {
	static String ruta = "/home/diurno/eclipse-workspace/AccesoDatos_T1/src/FlujosStreams";

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduce el nombre del fichero: ");
		String nombreFichero = scanner.nextLine();
		File archivo = new File(ruta, nombreFichero);

		if (!archivo.exists()) {
			System.out.println("El fichero no existe en la ruta indicada");
			return;
		}
		System.out.println("\nContenido actual del fichero:");
		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea;
			int contador = 1;
			while ((linea = reader.readLine()) != null) {
				System.out.println(contador + ". " + linea);
				contador++;
			}
		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
			return;
		}

		System.out.print("\nQue línea quieres modificar? ");
		int lineaAModificar = scanner.nextInt();
		scanner.nextLine();

		File archivoTemp = new File(ruta, "temp.txt");

		try (BufferedReader reader = new BufferedReader(new FileReader(archivo));
				BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemp))) {
			String linea;
			int numeroLinea = 1;

			while ((linea = reader.readLine()) != null) {
				if (numeroLinea != lineaAModificar) {
					writer.write(linea);
				} else {
					System.out.println("\nIntroduce los nuevos datos:");
					System.out.print("Nombre: ");
					String nombre = scanner.nextLine();

					System.out.print("Apellido: ");
					String apellido = scanner.nextLine();

					System.out.print("Teléfono: ");
					String telefono = scanner.nextLine();

					writer.write(nombre + ";" + apellido + ";" + telefono);
				}
				writer.newLine();
				numeroLinea++;
			}

		} catch (IOException e) {
			System.out.println("Error durante la lectura o reescritura: " + e.getMessage());
			return;
		}

		if (archivo.delete()) {
			if (archivoTemp.renameTo(archivo)) {
				System.out.println("\nLínea modificada correctamente");
			} else {
				System.out.println("Error al renombrar el fichero temporal");
			}
		} else {
			System.out.println("Error al borrar el fichero original");
		}

		System.out.println("\nContenido del fichero actualizado:");
		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea;
			int contador = 1;
			while ((linea = reader.readLine()) != null) {
				System.out.println(contador + ". " + linea);
				contador++;
			}
		} catch (IOException e) {
			System.out.println("Error al leer el fichero modificado: " + e.getMessage());
		}
	}
}
