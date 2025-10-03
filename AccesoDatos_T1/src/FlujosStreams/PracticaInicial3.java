package FlujosStreams;

public class PracticaInicial3 {
	public class EjemploSplit {
		public static void main(String[] args) {
			String frutas = "Fresa#Pera#Manzana#Platano#Naranja";
			String[] textoSeparado = frutas.split("#");
			for (String f : textoSeparado) {
				System.out.println(f);
			}
		}
	}
}
