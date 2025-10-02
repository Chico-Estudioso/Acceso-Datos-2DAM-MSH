package ejercicios;

import java.util.Scanner;

public class Ejercicio13 {

	static Scanner s = new Scanner(System.in);


	public static void main(String[] args) {
		
		int nota =0;
		nota = pedirNota();
		compararNota(nota);
	}

	private static void compararNota(int nota) {
		
		switch (nota) {
		case 0,1,2,3,4:
			System.out.println("): que pena has obtenido un insuficiente ");
			break;
		case 5:
			System.out.println("): puedes mejorar has obtenido un suficiente ");
			break;
		case 6:
			System.out.println(":)  has obtenido un bien , sigue mejorando ");
			break;
		case 7,8:
			System.out.println(":) genial ,  has obtenido un notable");
			break;
		case 9,10:
			System.out.println(":) perfecto has obtenido un sobresaliente");
			break;

		default:
			break;
		}
		
	}

	private static int pedirNota() {
		int nota = 0;
		boolean validar = true;
		
		while(validar) {
			System.out.println("introduzca el una nota entre 0 y 10");
			nota =s.nextInt();
			if(nota>=0 && nota<=10) {
				validar =false;
			}else {
				System.out.println("error nota invalido , vuelve a introducir una nota correcta");
			}
		}
		return nota;
		
	}
}
