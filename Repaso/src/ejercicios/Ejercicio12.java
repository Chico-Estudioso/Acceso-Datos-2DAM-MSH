package ejercicios;

import java.util.Scanner;

public class Ejercicio12 {
	static Scanner s = new Scanner(System.in);


	public static void main(String[] args) {
		
		double nota =0;
		nota = pedirNota();
		compararNota(nota);
	}

	private static void compararNota(double nota) {
		
		if(nota<5) {
			System.out.println("): que pena has obtenido un insuficiente ");
		}else if (nota>=5 && nota<6) {
			System.out.println("): puedes mejorar has obtenido un suficiente ");
		}else if (nota>=6 && nota<7) {
			System.out.println(":)  has obtenido un bien , sigue mejorando ");
		}else if (nota>=7 && nota<9) {
			System.out.println(":) genial ,  has obtenido un notable");
		}else {
			System.out.println(":) perfecto has obtenido un sobresaliente");
		}
		
	}

	private static double pedirNota() {
		double nota = 0;
		boolean validar = true;
		
		while(validar) {
			System.out.println("introduzca el una nota entre 0 y 10");
			nota =s.nextDouble();
			if(nota>=0 && nota<=10) {
				validar =false;
			}else {
				System.out.println("error nota invalido , vuelve a introducir una nota correcta");
			}
		}
		return nota;
		
	}
}
