package Ej7;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		Gestion gestionBanco = new Gestion();
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		int opcion = 0;
		
		while (opcion != 4) {
			System.out.println("\n--- MENÚ BANCO ---");
			System.out.println("1. Alta cliente");
			System.out.println("2. Baja cliente");
			System.out.println("3. Listar clientes");
			System.out.println("4. Salir");
			System.out.print("Elige una opción: ");
			
			opcion = teclado.nextInt();
			teclado.nextLine(); 

			if (opcion == 1) {
				System.out.print("Introduce DNI: ");
				String dni = teclado.nextLine();

				System.out.print("Introduce Nombre Completo: ");
				String nombre = teclado.nextLine();

				System.out.print("Introduce Fecha de Nacimiento (dd/mm/aaaa): ");
				String textoFecha = teclado.nextLine();
				LocalDate fechaNacimiento = LocalDate.parse(textoFecha, formatoFecha);

				System.out.print("Introduce Saldo: ");
				double saldo = teclado.nextDouble();
				teclado.nextLine(); 

				gestionBanco.altaCliente(dni, nombre, fechaNacimiento, saldo);

			} else if (opcion == 2) {
				System.out.print("Introduce el DNI del cliente a eliminar: ");
				String dniBorrar = teclado.nextLine();
				gestionBanco.bajaCliente(dniBorrar);

			} else if (opcion == 3) {
				gestionBanco.listarClientes();

			} else if (opcion == 4) {
				gestionBanco.guardarClientes();
				System.out.println("Saliendo y guardando datos...");

			} else {
				System.out.println("Opción incorrecta.");
			}
		}
		
		teclado.close();
	}
}
