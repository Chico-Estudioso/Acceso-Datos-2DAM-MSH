package EsquemasGenerales;

import java.util.Scanner;

public class EsquemaMenu {

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

	// Ejercicio 11
	public static void ejercicio1(Scanner scanner) {
	}

	// Ejercicio 12
	public static void ejercicio2(Scanner scanner) {

	}

	// Ejercicio 13
	public static void ejercicio3(Scanner scanner) {

	}

	// Ejercicio 14
	public static void ejercicio4(Scanner scanner) {

	}

	public static void ejercicio5(Scanner scanner) {

	}
}
