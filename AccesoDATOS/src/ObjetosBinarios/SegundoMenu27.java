package ObjetosBinarios;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class SegundoMenu27 {

	private static final String RUTA = "/home/diurno/Eclipse-AD/Acceso-Datos-2DAM-MSH/AccesoDATOS/src/ObjetosBinarios/empleados.bin";
	private static final String RUTATEMP = "/home/diurno/Eclipse-AD/Acceso-Datos-2DAM-MSH/AccesoDATOS/src/ObjetosBinarios/temp.bin";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
			System.out.println("1. Dar de alta empleado");
			System.out.println("2. Buscar empleado por DNI");
			System.out.println("3. Listar empleados");
			System.out.println("4. Borrar empleado por DNI");
			System.out.println("5. Salir");
			System.out.print("Elige opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine(); // limpiar buffer

			switch (opcion) {
			case 1:
				crearEmp(scanner);
				break;
			case 2:
				buscarDni(scanner);
				break;
			case 3:
				listarEmpleados();
				break;
			case 4:
				borrarEmpleado(scanner);
				break;
			case 5:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida");
			}

		} while (opcion != 5);

		scanner.close();
	}

	// Dar de alta empleado
	public static void crearEmp(Scanner scanner) {
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("DNI: ");
		String dni = scanner.nextLine();
		System.out.print("Sueldo: ");
		double sueldo = scanner.nextDouble();
		scanner.nextLine();

		Empleados emp = new Empleados(dni, nombre, sueldo);

		try {
			File file = new File(RUTA);
			boolean append = file.exists();

			FileOutputStream fos = new FileOutputStream(file, true);
			ObjectOutputStream oos;
			if (append) {
				oos = new MiObjectOutputStream(fos);
			} else {
				oos = new ObjectOutputStream(fos);
			}

			oos.writeObject(emp);
			oos.close();
			System.out.println("Empleado guardado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Buscar empleado por DNI
	public static void buscarDni(Scanner scanner) {
		System.out.print("DNI a buscar: ");
		String dniBus = scanner.nextLine();
		boolean encontrado = false;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA))) {
			while (true) {
				try {
					Empleados emp = (Empleados) ois.readObject();
					if (emp.getDni().equalsIgnoreCase(dniBus)) {
						System.out.println("Empleado encontrado: " + emp);
						encontrado = true;
						break;
					}
				} catch (EOFException eof) {
					break;
				}
			}
			if (!encontrado)
				System.out.println("No se ha encontrado empleado con ese DNI");
		} catch (FileNotFoundException fnf) {
			System.out.println("El fichero no existe todavía");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void listarEmpleados() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA))) {
			System.out.println("--- LISTA DE EMPLEADOS ---");
			while (true) {
				try {
					Empleados emp = (Empleados) ois.readObject();
					System.out.println(emp);
				} catch (EOFException eof) {
					break;
				}
			}
		} catch (FileNotFoundException fnf) {
			System.out.println("El fichero no existe todavía");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void borrarEmpleado(Scanner scanner) {
		System.out.print("DNI a borrar: ");
		String dniBorrar = scanner.nextLine();
		boolean encontrado = false;

		File temp = new File(RUTATEMP);
		File original = new File(RUTA);

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(original));
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(temp))) {

			while (true) {
				try {
					Empleados emp = (Empleados) ois.readObject();
					if (emp.getDni().equalsIgnoreCase(dniBorrar)) {
						encontrado = true;
						continue; // ESTO ES PA QUE ESE EMPLEADO NO SE COPIE, ES LA UNICA MANERA DE BORRAR UN
									// EMPLEADO HACIENDO FICHEROS TEMPORALES
					}
					oos.writeObject(emp);
				} catch (EOFException eof) {
					break;
				}
			}

			if (encontrado) {
				if (original.delete()) {
					temp.renameTo(original);
					System.out.println("Empleado borrado correctamente");
				}
			} else {
				temp.delete();
				System.out.println("No se encontró empleado con ese DNI");
			}

		} catch (FileNotFoundException fnf) {
			System.out.println("El fichero no existe todavía");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ESTO ES PARA QUE NO HAYA HEADERS NI NA
	static class MiObjectOutputStream extends ObjectOutputStream {
		public MiObjectOutputStream(OutputStream out) throws IOException {
			super(out);
		}

		@Override
		protected void writeStreamHeader() throws IOException {
			reset();
		}
	}
}