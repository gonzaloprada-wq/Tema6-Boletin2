package Ej1;

import java.io.BufferedReader;
import java.io.FileReader;

public class main {

	public static void main(String[] args) {

		String rutaCarta = "src/Ej1/carta";

		int numeroDeLineas = 0;

		int numeroDeCaracteres = 0;

		int numeroDePalabras = 0;

		try (BufferedReader bufferReader = new BufferedReader(new FileReader(rutaCarta))) {

			String linea = bufferReader.readLine();

			while (linea != null) {

				numeroDeLineas++;

				numeroDeCaracteres += linea.length();

				if (!linea.isEmpty()) {

					String[] palabrasArray = linea.split(" ");

					numeroDePalabras += palabrasArray.length;
				}

				linea = bufferReader.readLine();
			}

		} catch (Exception e) {

			System.out.println("Error al leer el archivo, no se encuentra disponible!");

		}

		imprimir(numeroDeLineas, numeroDeCaracteres, numeroDePalabras);

	}

	public static void imprimir(int numeroDeLineas, int numeroDeCaracteres, int numeroDePalabras) {

		System.out.println("Líneas: " + numeroDeLineas);
		System.out.println("Palabras: " + numeroDePalabras);
		System.out.println("Caracteres: " + numeroDeCaracteres);

	}
}
