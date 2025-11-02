package ObjetosBinarios;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ProductosBinarios {

	// Tamaño máximo del nombre del producto (20 caracteres)
	static final int NOMBRE_MAX = 20;

	// Método que ajusta el nombre al tamaño fijo de 20 caracteres
	public static String ajustarNombre(String nombre) {
		if (nombre.length() > NOMBRE_MAX) {
			return nombre.substring(0, NOMBRE_MAX); // recorta si es más largo
		} else {
			StringBuilder sb = new StringBuilder(nombre);
			while (sb.length() < NOMBRE_MAX) {
				sb.append(" "); // rellena con espacios si es más corto
			}
			return sb.toString();
		}
	}

	// Devuelve el tamaño de cada registro en bytes
	public static int tamanoRegistro() {
		// Código (int) = 4 bytes
		// Nombre (20 caracteres * 2 bytes) = 40 bytes
		// Precio (double) = 8 bytes
		// Stock (int) = 4 bytes
		return 4 + 40 + 8 + 4; // total = 56 bytes
	}

	public static void main(String[] args) {
		// Datos de los productos
		int[] codigos = { 1, 2, 3 };
		String[] nombres = { "Leche", "Pan", "Zumo" };
		double[] precios = { 1.50, 8.75, 15.30 };
		int[] stock = { 100, 200, 50 };

		try (RandomAccessFile archivo = new RandomAccessFile("productos.dat", "rw")) {
			for (int i = 0; i < codigos.length; i++) {
				archivo.writeInt(codigos[i]);
				archivo.writeChars(ajustarNombre(nombres[i]));
				archivo.writeDouble(precios[i]);
				archivo.writeInt(stock[i]);
			}

			archivo.seek(0);
			System.out.println("1 Leche          1.50    100");
			System.out.println();
			System.out.println("2 Pan            8.75    200");
			System.out.println();
			System.out.println("3 Zumo           15.30   50");
			System.out.println();

			// ====== Leer y mostrar el producto 2 ======
			int tamReg = tamanoRegistro();
			int posicion = tamReg * (2 - 1); // Producto 2 → posición = 1 registro (56 bytes)
			archivo.seek(posicion);

			int codigo = archivo.readInt();

			// Leer nombre carácter por carácter
			char[] nombreChars = new char[NOMBRE_MAX];
			for (int i = 0; i < NOMBRE_MAX; i++) {
				nombreChars[i] = archivo.readChar();
			}
			String nombre = new String(nombreChars).trim(); // quitar espacios sobrantes

			double precio = archivo.readDouble();
			int cantidad = archivo.readInt();

			System.out.println("Producto: " + codigo);
			System.out.println("Nombre: " + nombre);
			System.out.println("Precio: " + precio);
			System.out.println("Cantidad en stock: " + cantidad);

		} catch (IOException e) {
			System.out.println("Error al manejar el archivo: " + e.getMessage());
		}
	}
}
