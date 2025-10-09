package FlujosStreams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {
	static String ruta = "/home/diurno/eclipse-workspace/AccesoDatos_T1/src/FlujosStreams";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Elige un ejercicio (1-5):");
		int opcion = scanner.nextInt();
		scanner.nextLine();

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
			ejercicio5(scanner);
			break;
		default:
			System.out.println("Opción no válida. Elige un número del 1 al 5.");
		}

		scanner.close();
	}

	public static void ejercicio1(Scanner scanner) {
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

	public static void ejercicio2(Scanner scanner) {
		System.out.print("Introduce el nombre del fichero: ");
		String nombreFichero = scanner.nextLine();
		File archivo = new File(ruta, nombreFichero);

		boolean append = archivo.exists();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, append))) {
			String continuar;
			do {
				System.out.print("Introduce el nombre de la persona: ");
				String nombre = scanner.nextLine();

				System.out.print("Introduce el apellido de la persona: ");
				String apellido = scanner.nextLine();

				System.out.print("Introduce el teléfono de la persona: ");
				String tlfn = scanner.nextLine();

				writer.write(nombre + ";" + apellido + ";" + tlfn);
				writer.newLine();

				System.out.print("¿Deseas añadir otra persona? (s/n): ");
				continuar = scanner.nextLine().toLowerCase();
			} while (continuar.equals("s"));
			System.out.println("Datos añadidos correctamente.");
		} catch (IOException e) {
			System.out.println("Error al escribir en el fichero: " + e.getMessage());
		}
	}

	// LECTURA POR CARACTER
	public static void ejercicio3(Scanner scanner) {
		System.out.print("Introduce el nombre del fichero: ");
		String nombreFichero = scanner.nextLine();
		File archivo = new File(ruta, nombreFichero);

		try (FileReader fichero = new FileReader(archivo)) {
			int caracter;
			while ((caracter = fichero.read()) != -1) {
				System.out.print((char) caracter);
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
		}
	}

	// LECTURA POR LÍNEA
	public static void ejercicio4(Scanner scanner) {
		System.out.print("Introduce el nombre del fichero: ");
		String nombreFichero = scanner.nextLine();
		File archivo = new File(ruta, nombreFichero);

		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
		}
	}

	// Esto vale como apuntes para los ejercicios de usar Split con ;
	public static void ejercicio5(Scanner scanner) {
		System.out.print("Introduce el nombre del fichero: ");
		String nombreFichero = scanner.nextLine();
		File archivo = new File(ruta, nombreFichero);
		int contador = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] partes = linea.split(";");
				if (partes.length == 3) {
					System.out.println("Nombre: " + partes[0] + " Apellido: " + partes[1] + " Teléfono: " + partes[2]);
					contador++;
				}
			}
			System.out.println("Número total de personas: " + contador);
		} catch (Exception e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
		}
	}
}
