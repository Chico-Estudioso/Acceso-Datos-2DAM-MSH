package ObjetosBinarios;

import java.io.Serializable;

public class Empleados implements Serializable {
	private String dni;
	private String nombre;
	private double sueldo;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public Empleados(String dni, String nombre, double sueldo) {
		this.dni = dni;
		this.nombre = nombre;
		this.sueldo = sueldo;
	}

	public String toString() {
		return "Empleado [Nombre=" + nombre + ", DNI=" + dni + ", Sueldo=" + sueldo + "]";
	}

}
