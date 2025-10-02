package ejercicios;

import java.util.Scanner;

public class Ejercicio1 {
	
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		

		
		String palabraUno = pedirPalabra();
		String palabraDos = pedirPalabra();
		compararNumeros(palabraUno,palabraDos);
		
	}
	private static void compararNumeros(String palabraUno, String palabraDos) {
		if (palabraDos.equals(palabraUno)) {
			System.out.println(palabraUno +" es igual "+palabraDos);
		}else {
			System.out.println(palabraUno +" no es igual "+palabraDos);
		}
		
	}
	public static String pedirPalabra() {
		String palabra;
		System.out.println("Introduce la primera palabra");
		palabra = scanner.next();
		return palabra;
	}

}
