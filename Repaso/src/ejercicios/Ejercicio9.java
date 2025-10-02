package ejercicios;

import java.util.Scanner;

public class Ejercicio9 {

	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		
		int numero =0;
		numero=pedirNumero();
		compararNumero(numero);

	}

	private static void compararNumero(int numero) {

		if(numero>=18) {
			System.out.println("Eres mayor de edad");
		}else {
			System.out.println("eres menor de edad");
		}
	}

	private static int pedirNumero() {
		int numero = 0;
		System.out.println("introduzca la edad");
		numero =s.nextInt();
		return numero;
		
	}

}
