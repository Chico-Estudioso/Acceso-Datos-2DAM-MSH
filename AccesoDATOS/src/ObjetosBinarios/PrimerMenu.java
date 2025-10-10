package ObjetosBinarios;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class PrimerMenu {

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
			ejercicio5(scanner);
			break;
		default:
			System.out.println("Opción no válida. Elige un número del 1 al 5.");
		}

		scanner.close();
	}

	public static void ejercicio1(Scanner scanner) {
		// Guardar empleado
		System.out.println("Introduce el nombre del empleado:");
		String nombre = scanner.nextLine();
		System.out.println("Introduce el DNI del empleado:");
		String dni = scanner.nextLine();
		System.out.println("Introduce el sueldo del empleado:");
		double sueldo = scanner.nextDouble();
		scanner.nextLine();

		Empleados emp = new Empleados(dni, nombre, sueldo);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"/home/diurno/Eclipse-AD/Acceso-Datos-2DAM-MSH/AccesoDATOS/src/ObjetosBinarios/empleados.bin"))) {
			oos.writeObject(emp);
			System.out.println("Empleado guardado correctamente en empleado.bin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio2(Scanner scanner) {
		// Leer empleado
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"/home/diurno/Eclipse-AD/Acceso-Datos-2DAM-MSH/AccesoDATOS/src/ObjetosBinarios/empleados.bin"))) {
			Empleados emp = (Empleados) ois.readObject();
			System.out.println("Empleado leído del fichero:");
			System.out.println(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ejercicio3(Scanner scanner) {

	}

	public static void ejercicio4(Scanner scanner) {

	}

	public static void ejercicio5(Scanner scanner) {

	}
}
