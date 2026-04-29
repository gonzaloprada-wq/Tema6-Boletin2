package Ej2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class gestionFirmas {

	private ArrayList<String> firmas = new ArrayList<>();

	private String rutaArchivo = "src/Ej2/firmas";

	public void cargarFirmas() {

		try (BufferedReader bufferReader = new BufferedReader(new FileReader(rutaArchivo))) {

			String linea;

			while ((linea = bufferReader.readLine()) != null) {

				firmas.add(linea);
			}
		} catch (Exception e) {

		}
	}

	public void guardarFirmas() {

		try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(rutaArchivo))) {

			for (String nombre : firmas) {

				bufferWriter.write(nombre);

				bufferWriter.newLine();
			}

			bufferWriter.flush();

		} catch (Exception e) {

			System.out.println("Error al guardar: " + e.getMessage());

		}
	}

	public boolean insertarNombre(String nombre) {

		boolean seHaAñadido = false;

		if (!firmas.contains(nombre)) {

			firmas.add(nombre);

			seHaAñadido = true;
		}
		return seHaAñadido;
	}

	public void mostrarLibro() {

		if (firmas.size() == 0) {

			System.out.println("El libro está vacío.");

		} else {

			for (String firmaActual : firmas) {

				System.out.println("---" + firmaActual);

			}
			;
		}
	}

}
