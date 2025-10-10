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

		System.out.println("Elige un ejercicio (1-9):");
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
		case 6:
			ejercicio6(scanner);
			break;
		case 7:
			ejercicio7(scanner);
			break;
		case 8:
			ejercicio8(scanner);
			break;
		case 9:
			ejercicio9(scanner);
			break;
		default:
			System.out.println("Opción no válida. Elige un número del 1 al 9");
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

	public static void ejercicio6(Scanner scanner) {
		System.out.println("Introduce la ruta del fichero que quieras buscar:");
		String nFichero = scanner.nextLine();
		File archivo = new File(ruta, nFichero);

		if (!archivo.exists()) {
			System.out.println("El fichero no existe en la ruta indicada");
			return;
		}
		// Copiamos el método de leer líneas para leer el fichero
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

		System.out.print("\n Que línea quieres borrar? ");
		int lineaABorrar = scanner.nextInt();
		scanner.nextLine();

		File archivoTemp = new File(ruta, "temp.txt");

		try (BufferedReader reader = new BufferedReader(new FileReader(archivo));
				BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemp))) {
			String linea;
			int numeroLinea = 1;
			// Leemos como siempre
			while ((linea = reader.readLine()) != null) {
				if (numeroLinea != lineaABorrar) {
					// Con el primero buscamos las lineas que vamos a sovbrescribir menos la linea a
					// borrar
					writer.write(linea);
					// Y se colocan en el fichero temporal que hemos hecho
					writer.newLine();
				}
				numeroLinea++;
			}
		} catch (IOException e) {
			System.out.println("Error durante la lectura o reescripcion: " + e.getMessage());
			return;
		}

		if (archivo.delete()) {
			if (archivoTemp.renameTo(archivo)) {
				System.out.println("\nLínea borrada correctamente");
			} else {
				System.out.println("Error al renombrar el fichero temporal");
			}
		} else {
			System.out.println("Error al borrar el fichero original");
		}

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

	public static void ejercicio7(Scanner scanner) {
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

	public static void ejercicio8(Scanner scanner) {
		System.out.print("Introduce el nombre del fichero original: ");
		String nombreOriginal = scanner.nextLine();
		File archivoOriginal = new File(ruta, nombreOriginal);

		if (!archivoOriginal.exists()) {
			System.out.println("El fichero no existe en la ruta indicada");
			return;
		}

		System.out.print("Introduce el nombre del fichero destino: ");
		String nombreDestino = scanner.nextLine();
		File archivoDestino = new File(ruta, nombreDestino);

		try (BufferedReader reader = new BufferedReader(new FileReader(archivoOriginal));
				BufferedWriter writer = new BufferedWriter(new FileWriter(archivoDestino))) {
			String linea;
			// leer por carácteres
			while ((linea = reader.readLine()) != null) {
				// Esto te ayuda a optimizar la carga en vez de hacer un String de cada ve
				StringBuilder lineaTransformada = new StringBuilder();
				for (char c : linea.toCharArray()) {
					if (Character.isUpperCase(c)) {
						lineaTransformada.append(Character.toLowerCase(c));
					} else if (Character.isLowerCase(c)) {
						lineaTransformada.append(Character.toUpperCase(c));
					} else {
						lineaTransformada.append(c);
					}
				}
				writer.write(lineaTransformada.toString());
				writer.newLine();
			}
			System.out.println("\nFichero transformado correctamente: " + nombreDestino);
		} catch (IOException e) {
			System.out.println("Error al leer o escribir ficheros: " + e.getMessage());
		}
	}

	public static void ejercicio9(Scanner scanner) {
		File archivoCotizacion = new File(
				"/home/diurno/eclipse-workspace/AccesoDatos_T1/src/FlujosStreams/ej19_cotizacion.txt");
		File archivoCotizacion2 = new File(ruta, "cotizacion2.txt");
		try (BufferedReader reader = new BufferedReader(new FileReader(archivoCotizacion));
				BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCotizacion2))) {
			String linea;
			int numeroLinea = 1;

			while ((linea = reader.readLine()) != null) {
				int modulo = numeroLinea % 5;

				if (modulo == 2 || modulo == 3) {
					// Escribies solo las líneas 2 y 3 de cada bloque de 5 líneas pero cuidao que
					// esto solo funciona hasta el 100
					writer.write(linea);
					writer.newLine();
				}

				numeroLinea++;
			}

		} catch (IOException e) {
			System.out.println("Error durante la lectura o reescritura: " + e.getMessage());
			return;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(archivoCotizacion2))) {
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

}
