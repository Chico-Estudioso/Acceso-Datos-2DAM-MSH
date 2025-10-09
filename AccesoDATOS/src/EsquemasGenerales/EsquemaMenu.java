package EsquemasGenerales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EsquemaMenu {
	static String ruta = "/home/diurno/eclipse-workspace/AccesoDatos_T1/src/FlujosStreams";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Elige un ejercicio (1-5):");
		int opcion = scanner.nextInt();
		scanner.nextLine(); // Limpia el buffer

		switch (opcion) {
		case 1:
			ejercicio1(scanner);
			break;
		case 2:
			ejercicio2(scanner);
			break;
		case 3:
			ejercicio3(scanner);
			break;
		case 4:
			ejercicio4(scanner);
			break;
		case 5:
			ejercicio5();
			break;
		default:
			System.out.println("Opción no válida. Elige un número del 1 al 5.");
		}

		scanner.close();
	}

	// Ejercicio 11
	public static void ejercicio1(Scanner scanner) {
		System.out.println("Ejecutando el Ejercicio 1 (crear fichero y añadir texto)...");

		System.out.print("Introduce el nombre del fichero: ");
		String nombreFichero = scanner.nextLine();

		System.out.print("Introduce el texto a añadir: ");
		String texto = scanner.nextLine();

		File archivo = new File(ruta, nombreFichero);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
			writer.write(texto);
			System.out.println("Texto escrito correctamente en el fichero.");
		} catch (IOException e) {
			System.out.println("Error al escribir en el fichero: " + e.getMessage());
		}
	}

	// Ejercicio 12
	public static void ejercicio2(Scanner scanner) {
		System.out.println("Ejecutando el Ejercicio 2 (añadir registros de personas)...");

		System.out.print("Introduce el nombre del fichero: ");
		String nombreFichero = scanner.nextLine();

		File archivo = new File(ruta, nombreFichero);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
			String continuar;
			do {
				System.out.print("Nombre: ");
				String nombre = scanner.nextLine();

				System.out.print("Apellido: ");
				String apellido = scanner.nextLine();

				System.out.print("Teléfono: ");
				String telefono = scanner.nextLine();

				writer.write(nombre + ";" + apellido + ";" + telefono);
				writer.newLine();

				System.out.print("¿Deseas añadir otra persona? (s/n): ");
				continuar = scanner.nextLine().toLowerCase();

			} while (continuar.equals("s"));

			System.out.println("Datos añadidos correctamente.");
		} catch (IOException e) {
			System.out.println("Error al escribir en el fichero: " + e.getMessage());
		}
	}

	// Ejercicio 13
	public static void ejercicio3(Scanner scanner) {
		System.out.println("Ejecutando el Ejercicio 3 (leer por carácter)...");

		System.out.print("Introduce la ruta del fichero a leer: ");
		String rutaFichero = scanner.nextLine();

		File archivo = new File(rutaFichero);

		try (FileReader reader = new FileReader(archivo)) {
			int caracter;
			while ((caracter = reader.read()) != -1) {
				System.out.print((char) caracter);
			}
		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
		}
	}

	// Ejercicio 14
	public static void ejercicio4(Scanner scanner) {
		System.out.println("Ejecutando el Ejercicio 4 (leer por línea)...");

		System.out.print("Introduce la ruta del fichero a leer: ");
		String rutaFichero = scanner.nextLine();

		File archivo = new File(rutaFichero);

		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
		}
	}

	public static void ejercicio5() {
		System.out.println("Ejecutando el Ejercicio 5...");
		// Aquí puedes añadir el código para el ejercicio 5 cuando lo tengas
	}
}
