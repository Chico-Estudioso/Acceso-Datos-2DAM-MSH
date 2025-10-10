package ObjetosBinarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu28ConArrayList {

	private static final String RUTA = "/home/diurno/Eclipse-AD/Acceso-Datos-2DAM-MSH/AccesoDATOS/src/ObjetosBinarios/empleados2.bin";
	private static ArrayList<Empleados> listaEmpleados = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		cargarEmpleados();

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
			scanner.nextLine();

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
				guardarEmpleados();
				break;
			default:
				System.out.println("Opción no válida");
			}

		} while (opcion != 5);

		scanner.close();
	}

	private static void crearEmp(Scanner scanner) {
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("DNI: ");
		String dni = scanner.nextLine();
		System.out.print("Sueldo: ");
		double sueldo = scanner.nextDouble();
		scanner.nextLine();

		for (Empleados emp : listaEmpleados) {
			if (emp.getDni().equalsIgnoreCase(dni)) {
				System.out.println("Ya existe un empleado con ese DNI");
				return;
			}
		}

		Empleados emp = new Empleados(dni, nombre, sueldo);
		listaEmpleados.add(emp);
		System.out.println("Empleado añadido correctamente");
	}

	private static void buscarDni(Scanner scanner) {
		System.out.print("DNI a buscar: ");
		String dniBus = scanner.nextLine();
		boolean encontrado = false;

		for (Empleados emp : listaEmpleados) {
			if (emp.getDni().equalsIgnoreCase(dniBus)) {
				System.out.println("Empleado encontrado: " + emp);
				encontrado = true;
				break;
			}
		}

		if (!encontrado)
			System.out.println("No se ha encontrado empleado con ese DNI");
	}

	private static void listarEmpleados() {
		System.out.println("--- LISTA DE EMPLEADOS ---");
		if (listaEmpleados.isEmpty()) {
			System.out.println("No hay empleados");
		} else {
			for (Empleados emp : listaEmpleados) {
				System.out.println(emp);
			}
		}
	}

	private static void borrarEmpleado(Scanner scanner) {
		System.out.print("DNI a borrar: ");
		String dniBorrar = scanner.nextLine();
		boolean eliminado = false;

		for (int i = 0; i < listaEmpleados.size(); i++) {
			if (listaEmpleados.get(i).getDni().equalsIgnoreCase(dniBorrar)) {
				listaEmpleados.remove(i);
				eliminado = true;
				break;
			}
		}

		if (eliminado) {
			System.out.println("Empleado borrado correctamente");
		} else {
			System.out.println("No se encontró empleado con ese DNI");
		}
	}

	private static void guardarEmpleados() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA))) {
			oos.writeObject(listaEmpleados);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private static void cargarEmpleados() {
		File file = new File(RUTA);
		if (!file.exists())
			return;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			listaEmpleados = (ArrayList<Empleados>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
