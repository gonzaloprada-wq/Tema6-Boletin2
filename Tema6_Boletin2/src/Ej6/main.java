package Ej6;

import java.io.BufferedReader;
import java.io.FileReader;

public class main {

	static int maximaEdad = -1;
	static String nombreMaximaEdad = "";

	static double maximoPeso = -1;
	static String nombreMaximoPeso = "";

	static double maximaEstatura = -1;
	static String nombreMaximaEstatura = "";

	public static void main(String[] args) {
		String rutaFichero = "src/Ej6/deportistas";

		leerArchivo(rutaFichero);
		imprimirDatos();
	}

	public static void leerArchivo(String rutaFichero) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaFichero))) {

			bufferedReader.readLine();
			String linea;

			while ((linea = bufferedReader.readLine()) != null) {
				if (!linea.trim().isEmpty()) {
					procesar(linea.trim());
				}
			}

		} catch (Exception excepcion) {
			System.out.println("Error al leer el fichero: " + excepcion.getMessage());
		}
	}

	public static void procesar(String linea) {
		String[] partes = linea.split("\\s+");
		int totalPartes = partes.length;

		if (totalPartes >= 4) {
			double estatura = Double.parseDouble(partes[totalPartes - 1].replace(",", "."));
			double peso = Double.parseDouble(partes[totalPartes - 2].replace(",", "."));
			int edad = Integer.parseInt(partes[totalPartes - 3]);

			String nombreCompleto = "";
			for (int i = 0; i < totalPartes - 3; i++) {
				nombreCompleto += partes[i] + " ";
			}
			nombreCompleto = nombreCompleto.trim();

			actualizarMaximos(nombreCompleto, edad, peso, estatura);
		}
	}

	public static void actualizarMaximos(String nombreCompleto, int edad, double peso, double estatura) {
		if (edad > maximaEdad) {
			maximaEdad = edad;
			nombreMaximaEdad = nombreCompleto;
		}
		if (peso > maximoPeso) {
			maximoPeso = peso;
			nombreMaximoPeso = nombreCompleto;
		}
		if (estatura > maximaEstatura) {
			maximaEstatura = estatura;
			nombreMaximaEstatura = nombreCompleto;
		}
	}

	public static void imprimirDatos() {
		System.out.println("--- RESULTADOS ---");
		System.out.println("Deportista de mayor edad: " + nombreMaximaEdad + " (" + maximaEdad + " años)");
		System.out.println("Deportista de mayor peso: " + nombreMaximoPeso + " (" + maximoPeso + " kg)");
		System.out.println("Deportista de mayor estatura: " + nombreMaximaEstatura + " (" + maximaEstatura + " m)");
	}
}