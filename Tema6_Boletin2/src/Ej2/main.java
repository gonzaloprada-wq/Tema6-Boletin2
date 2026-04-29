package Ej2;

import java.util.Scanner;

public class main {

	private static Scanner teclado = new Scanner(System.in);

	private static gestionFirmas gestor = new gestionFirmas();

	public static void main(String[] args) {

		gestor.cargarFirmas();

		int opcion;

		do {
			opcion = Menu();

			ejecutarOpcion(opcion);

		} while (opcion != 3);

		teclado.close();
	}

	public static int Menu() {
		System.out.println("\n--- BIENVENIDO AL LIBRO DE FIRMAS ---");
		System.out.println("1. Ver todas las firmas");
		System.out.println("2. Añadir mi firma");
		System.out.println("3. Guardar y Salir");
		System.out.print("Selecciona una opción: ");

		try {
			return Integer.parseInt(teclado.nextLine());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static void ejecutarOpcion(int opcion) {
		switch (opcion) {

		case 1:
			menuMostrarFirmas();

			break;
		case 2:
			menuInsertarFirma();
			break;
		case 3:
			menuGuardarYSalir();
			break;
		default:
			System.out.println("Opción no válida, intenta de nuevo.");
			break;
		}
	}

	public static void menuMostrarFirmas() {
		
		System.out.println("\n---LISTADO DE FIRMAS");
		
		gestor.mostrarLibro();
	}

	public static void menuInsertarFirma() {
		
		System.out.print("Introduce tu nombre para firmar: ");
		
		String nombre = teclado.nextLine();

		if (gestor.insertarNombre(nombre)) {
			
			System.out.println("Firma añadida");
			
		} else {
			
			System.out.println("Ya has firmado anteriormente.");
			
		}
	}

	public static void menuGuardarYSalir() {
		
		System.out.println("Guardando cambios en el archivo...");
		
		gestor.guardarFirmas();
		
		System.out.println("¡Adiós!");
	}
}