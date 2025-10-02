package ejercicios;

import java.util.Scanner;

public class Ejercicio11 {

	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		
		int numero =0;
		numero=pedirNumero();
		diaSemana(numero);
	}

	private static void diaSemana(int numero) {
		switch (numero) {
		case 1:
			System.out.println("hoy es lunes");
			break;
		case 2:
			System.out.println("hoy es martes");
			break;
		case 3:
			System.out.println("hoy es miercoles");
			break;
		case 4:
			System.out.println("hoy es jueves");
			break;
		case 5:
			System.out.println("hoy es viernes");
			break;
		case 6:
			System.out.println("hoy es sabado");
			break;
		case 7:
			System.out.println("hoy es domingo");
			break;

		default:
			System.err.println("error en el dia del mes");
			break;
		}
		
	}

	private static int pedirNumero() {
		int numero = 0;
		boolean validar = true;
		
		while(validar) {
			System.out.println("introduzca el numero del 1 al 7 ");
			numero =s.nextInt();
			if(numero>0 && numero<8) {
				validar =false;
			}else {
				System.out.println("error numero invalido , vuelve a introducir el numero del 1 al 7");
			}
		}
		return numero;
		
	}
}
