package Ej7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Gestion {

	private ArrayList<Cliente> listaClientes;
	private String rutaArchivo = "clientes.txt";
	private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Gestion() {
		listaClientes = new ArrayList<>();
		cargarClientes();
	}

	private void cargarClientes() {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			while ((linea = bufferedReader.readLine()) != null) {
				if (!linea.trim().isEmpty()) {
					String[] partes = linea.split(";");
					
					String dni = partes[0];
					String nombre = partes[1];
					LocalDate fecha = LocalDate.parse(partes[2], formatoFecha);
					double saldo = Double.parseDouble(partes[3]);
					
					Cliente nuevoCliente = new Cliente(dni, nombre, fecha, saldo);
					listaClientes.add(nuevoCliente);
				}
			}
		} catch (Exception excepcion) {
			System.out.println("Aviso: No se ha podido cargar el archivo o no existe todavía.");
		}
	}

	public void guardarClientes() {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(rutaArchivo))) {
			for (int i = 0; i < listaClientes.size(); i++) {
				Cliente actual = listaClientes.get(i);
				String fechaFormateada = actual.getFechaNacimiento().format(formatoFecha);
				
				bufferedWriter.write(actual.getDni() + ";" + 
				                     actual.getNombre() + ";" + 
				                     fechaFormateada + ";" + 
				                     actual.getSaldo());
				bufferedWriter.newLine();
			}
		} catch (Exception excepcion) {
			System.out.println("Error al guardar los datos en el archivo.");
		}
	}

	public void altaCliente(String dni, String nombre, LocalDate fecha, double saldo) {
		Cliente nuevoCliente = new Cliente(dni, nombre, fecha, saldo);
		listaClientes.add(nuevoCliente);
		
		listaClientes.sort((cliente1, cliente2) -> cliente1.getDni().compareTo(cliente2.getDni()));
		System.out.println("Cliente dado de alta correctamente.");
	}

	public void bajaCliente(String dniBorrar) {
		boolean encontrado = false;
		for (int i = 0; i < listaClientes.size(); i++) {
			if (listaClientes.get(i).getDni().equalsIgnoreCase(dniBorrar)) {
				listaClientes.remove(i);
				encontrado = true;
				System.out.println("Cliente eliminado correctamente.");
				break; 
			}
		}

		if (!encontrado) {
			System.out.println("No se ha encontrado ningún cliente con ese DNI.");
		}
	}

	public void listarClientes() {
		if (listaClientes.isEmpty()) {
			System.out.println("No hay clientes registrados.");
			return;
		}

		double saldoMaximo = listaClientes.get(0).getSaldo();
		double saldoMinimo = listaClientes.get(0).getSaldo();
		double sumaSaldos = 0;

		System.out.println("\n--- LISTA DE CLIENTES ---");
		for (int i = 0; i < listaClientes.size(); i++) {
			Cliente actual = listaClientes.get(i);
			
			System.out.println("DNI: " + actual.getDni() + 
			                   " | Nombre: " + actual.getNombre() + 
			                   " | Saldo: " + actual.getSaldo() + "€" +
			                   " | Edad: " + actual.getEdad() + " años");

			double saldoActual = actual.getSaldo();
			sumaSaldos += saldoActual;

			if (saldoActual > saldoMaximo) {
				saldoMaximo = saldoActual;
			}
			if (saldoActual < saldoMinimo) {
				saldoMinimo = saldoActual;
			}
		}

		double promedioSaldos = sumaSaldos / listaClientes.size();

		System.out.println("\n--- ESTADÍSTICAS ---");
		System.out.println("Saldo Máximo: " + saldoMaximo + "€");
		System.out.println("Saldo Mínimo: " + saldoMinimo + "€");
		System.out.println("Promedio de Saldos: " + promedioSaldos + "€");
	}
}