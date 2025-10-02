package ejercicios;

import java.util.Scanner;

public class Ejercicio10 {

	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		
		int numero =0;
		numero=pedirNumero();
		compararNumero(numero);

	}

	private static void compararNumero(int numero) {

		if(numero>=0) {
			if(numero % 2==0) {
				System.out.println("el numero "+numero +" es positivo y par");
			}else {
				System.out.println("el numero "+numero +" es positivo e impar");
			}
				
			
		}else {
			System.out.println("el numero "+numero +" es negativo");
		}
	}

	private static int pedirNumero() {
		int numero = 0;
		System.out.println("introduzca el numero");
		numero =s.nextInt();
		return numero;
		
	}

}
