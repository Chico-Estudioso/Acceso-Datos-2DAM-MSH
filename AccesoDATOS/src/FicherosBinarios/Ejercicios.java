package FicherosBinarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicios {
	static String ruta = "/home/diurno/Eclipse-AD/Acceso-Datos-2DAM-MSH/AccesoDATOS/src/FicherosBinarios";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Elige un ejercicio (1-5):");
		int opcion = scanner.nextInt();
		scanner.nextLine();

		switch (opcion) {
		case 1:
			ejercicio21(scanner);
			break;
		case 2:
			ejercicio22(scanner);
			break;
		case 3:
			ejercicio23(scanner);
			break;
		case 4:
			ejercicio24(scanner);
			break;
		case 5:
			ejercicio25(scanner);
			break;
		default:
			System.out.println("Opción no válida. Elige un número del 1 al 5.");
		}

		scanner.close();
	}

	// CUIDAO, ESTE EJERCICIO NO VALE COMO ESCRIBIR UN FICHERO COMPLETO, SOLO
	// ESCRIBE 1 RESULTADO DE AHI QUE NO AHAY BUCLE
	public static void ejercicio21(Scanner scanner) {
		try {
			System.out.print("Introduce la ruta del fichero que se va a generar: ");
			String ruta = scanner.nextLine();

			System.out.print("Introduce un número entre 32 y 126: ");
			int numero = scanner.nextInt();

			if (numero < 32 || numero > 126) {
				System.out.println("El número debe estar entre 32 y 126 (rango ASCII visible)");
				return;
			}

			try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta))) {
				dos.writeInt(numero);
				System.out.println("Fichero creado correctamente en: " + new File(ruta).getAbsolutePath());
				System.out.println("Número almacenado: " + numero + " → Carácter ASCII: " + (char) numero);
			}

		} catch (IOException e) {
			System.out.println("Error al crear el fichero: " + e.getMessage());
		}

	}

	public static void ejercicio22(Scanner scanner) {
		try {
			System.out.print("Introduce la ruta del fichero que contiene un número: ");
			String ruta = scanner.nextLine();

			try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
				int numero = dis.readInt();
				System.out.println("El fichero contiene el número: " + numero + " - Carácter: " + (char) numero);
			}

		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
		}

	}

	public static void ejercicio23(Scanner scanner) {
		try {
			System.out.print("Introduce la ruta donde quieres hacer del fichero de alumnos: ");
			String ruta = scanner.nextLine();

			try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta))) {
				String continuar;
				do {
					System.out.print("Introduce el número de expediente: ");
					int expediente = Integer.parseInt(scanner.nextLine());

					System.out.print("Introduce el nombre del alumno: ");
					String nombre = scanner.nextLine();

					System.out.print("Introduce la nota del alumno: ");
					double nota = Double.parseDouble(scanner.nextLine());
					dos.writeInt(expediente);
					dos.writeDouble(nota);

					for (char c : (nombre + "\n").toCharArray()) {
						dos.writeChar(c);
					}

					System.out.println("Quieres introducir otro alumno (s/n): ");
					continuar = scanner.nextLine();
				} while (continuar.equalsIgnoreCase("s"));

				System.out.println("Fichero creado correctamente");
			}

		} catch (IOException e) {
			System.out.println("Error al escribir el fichero: " + e.getMessage());
		}
	}

	public static void ejercicio24(Scanner scanner) {
		try {
			System.out.print("Introduce la ruta del fichero: ");
			String ruta = scanner.nextLine();

			try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
				// Solo entra si hay algun byte pa leer
				while (dis.available() > 0) {
					int expediente = dis.readInt();
					double nota = dis.readDouble();

					// Recorre todo hasta que llegue al salto de linea, pero creo que con el readUTF
					// se soluciona est eproblema
					StringBuilder nombre = new StringBuilder();
					char c;
					while ((c = dis.readChar()) != '\n') {
						nombre.append(c);
					}

					System.out.println("Expediente: " + expediente + "  Nota: " + nota + "  Nombre: " + nombre);
				}
			}

		} catch (EOFException e) {
			System.out.println("Fin del fichero");
		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
		}
	}

	public static void ejercicio25(Scanner scanner) {
		try {
			System.out.print("Introduce la ruta del fichero que deseas convertir: ");
			String ruta = scanner.nextLine();

			File original = new File(ruta);
			File temporal = new File("src/FicherosBinarios/temporal.tmp");

			// Abrimos el fichero original para lectura y uno temporal para escritura ESTE
			// NO ES BINARIO
			try (FileReader fr = new FileReader(original); FileWriter fw = new FileWriter(temporal)) {

				int caracter;
				while ((caracter = fr.read()) != -1) { // -1 es el final del archivo
					char c = (char) caracter;

					// Si es una vocal minuscula, la convertimos a mayus
					if ("aeiou".indexOf(c) != -1) {
						c = Character.toUpperCase(c);
					}

					// Escribimos el caracter que sea
					fw.write(c);
				}
			}

			if (original.delete()) {
				if (temporal.renameTo(original)) {
					System.out.println("Vocales convertidas a mayúsculas correctamente");
				} else {
					System.out.println("No se pudo renombrar el fichero temporal");
				}
			} else {
				System.out.println("No se pudo eliminar el fichero original");
			}

		} catch (IOException e) {
			System.out.println("Error en el proceso: " + e.getMessage());
		}
	}

}
