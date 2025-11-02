package ObjetosBinarios;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class GestionProductos {

	static final int NOMBRE_MAX = 20;
	static final String ARCHIVO = "productos.dat";

	public static String ajustarNombre(String nombre) {
		if (nombre.length() > NOMBRE_MAX)
			return nombre.substring(0, NOMBRE_MAX);
		StringBuilder sb = new StringBuilder(nombre);
		while (sb.length() < NOMBRE_MAX)
			sb.append(" ");
		return sb.toString();
	}

	public static int tamanoRegistro() {
		return 4 + (NOMBRE_MAX * 2) + 8 + 4;
	}

	public static void altaProducto() throws IOException {
		try (RandomAccessFile file = new RandomAccessFile(ARCHIVO, "rw")) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Código del producto: ");
			int codigo = sc.nextInt();
			sc.nextLine();
			System.out.print("Nombre del producto: ");
			String nombre = sc.nextLine();
			System.out.print("Precio: ");
			double precio = sc.nextDouble();
			System.out.print("Cantidad en stock: ");
			int stock = sc.nextInt();

			file.seek(file.length());
			file.writeInt(codigo);
			file.writeChars(ajustarNombre(nombre));
			file.writeDouble(precio);
			file.writeInt(stock);

			System.out.println("✅ Producto añadido correctamente");
		}
	}

	public static void listarProductos() throws IOException {
		try (RandomAccessFile file = new RandomAccessFile(ARCHIVO, "r")) {
			int tamReg = tamanoRegistro();
			while (file.getFilePointer() < file.length()) {
				int codigo = file.readInt();
				char[] nombreChars = new char[NOMBRE_MAX];
				for (int i = 0; i < NOMBRE_MAX; i++)
					nombreChars[i] = file.readChar();
				String nombre = new String(nombreChars).trim();
				double precio = file.readDouble();
				int stock = file.readInt();

				if (!nombre.startsWith("*")) {
					System.out.printf("Código: %d | Nombre: %s | Precio: %.2f | Stock: %d%n", codigo, nombre, precio,
							stock);
				}
			}
		}
	}

	public static void modificarStock() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce código del producto: ");
		int codigoBuscar = sc.nextInt();

		try (RandomAccessFile file = new RandomAccessFile(ARCHIVO, "rw")) {
			int tamReg = tamanoRegistro();
			while (file.getFilePointer() < file.length()) {
				long posInicio = file.getFilePointer();
				int codigo = file.readInt();
				char[] nombreChars = new char[NOMBRE_MAX];
				for (int i = 0; i < NOMBRE_MAX; i++)
					nombreChars[i] = file.readChar();
				String nombre = new String(nombreChars).trim();
				double precio = file.readDouble();
				int stock = file.readInt();

				if (codigo == codigoBuscar && !nombre.startsWith("*")) {
					System.out.print("Nuevo stock: ");
					int nuevoStock = sc.nextInt();
					file.seek(posInicio + 4 + (NOMBRE_MAX * 2) + 8);
					file.writeInt(nuevoStock);
					System.out.println("Stock modificado correctamente");
					return;
				}
			}
			System.out.println("⚠️ Producto no encontrado");
		}
	}

	public static void borrarProducto() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Código del producto a borrar: ");
		int codigoBuscar = sc.nextInt();

		try (RandomAccessFile file = new RandomAccessFile(ARCHIVO, "rw")) {
			int tamReg = tamanoRegistro();
			while (file.getFilePointer() < file.length()) {
				long posInicio = file.getFilePointer();
				int codigo = file.readInt();
				long posNombre = file.getFilePointer();

				char[] nombreChars = new char[NOMBRE_MAX];
				for (int i = 0; i < NOMBRE_MAX; i++)
					nombreChars[i] = file.readChar();
				String nombre = new String(nombreChars).trim();

				if (codigo == codigoBuscar && !nombre.startsWith("*")) {
					file.seek(posNombre);
					file.writeChars(ajustarNombre("*" + nombre)); // marca con *
					System.out.println("Producto borrado correctamente");
					return;
				}

				file.seek(posInicio + tamReg);
			}
			System.out.println("Producto no encontrado");
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("\n=== GESTIÓN DE PRODUCTOS ===");
			System.out.println("1. Alta producto");
			System.out.println("2. Listar productos");
			System.out.println("3. Modificar stock");
			System.out.println("4. Borrar producto");
			System.out.println("5. Salir");
			System.out.print("Opción: ");
			opcion = sc.nextInt();

			switch (opcion) {
			case 1 -> altaProducto();
			case 2 -> listarProductos();
			case 3 -> modificarStock();
			case 4 -> borrarProducto();
			case 5 -> System.out.println("Saliendo del programa...");
			default -> System.out.println("Opción no válida");
			}
		} while (opcion != 5);
	}
}
