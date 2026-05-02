package Ej4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class main {

	public static void main(String[] args) {

		
		String rutaCodec = "src/Ej4/codec";
		
		String rutaOriginal = "src/Ej4/mensaje";
		
		String rutaDestino = "src/Ej4/mensaje_cifrado";
		

		String[] alfabetos = cargarClaves(rutaCodec);

		if (alfabetos != null) {

			procesarArchivo(rutaOriginal, rutaDestino, alfabetos);

			System.out.println("Proceso finalizado con éxito.");
		}
	}

	public static String[] cargarClaves(String ruta) {

		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String linea1 = br.readLine();
			String linea2 = br.readLine();

			if (linea1 != null && linea2 != null) {

				String normal = linea1.split(":")[1].replace(" ", "").trim();

				String cifrado = linea2.split(":")[1].replace(" ", "").trim();

				return new String[] { normal, cifrado };
			}

		} catch (IOException e) {
			System.out.println("Error al cargar el archivo de claves.");
		}
		return null;
	}

	public static void procesarArchivo(String entrada, String salida, String[] alfabetos) {

		try (BufferedReader bufferReader = new BufferedReader(new FileReader(entrada));

				BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(salida))) {

			String linea;
			while ((linea = bufferReader.readLine()) != null) {

				for (int i = 0; i < linea.length(); i++) {

					char caracter = linea.charAt(i);

					bufferWriter.write(encriptarCaracter(caracter, alfabetos[0], alfabetos[1]));
				}
				bufferWriter.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error en la lectura/escritura de mensajes.");
		}
	}

	public static char encriptarCaracter(char caracter, String normal, String cifrado) {
		
		int pos = normal.indexOf(caracter);
		
		return (pos != -1) ? cifrado.charAt(pos) : caracter;
	}
}