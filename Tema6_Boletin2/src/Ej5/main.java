package Ej5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main {

    public static void main(String[] args) {
        
        String rutaArchivo1 = "src/Ej5/archivo1";
        String rutaArchivo2 = "src/Ej5/archivo2";

        compararFicheros(rutaArchivo1, rutaArchivo2);
    }

    public static void compararFicheros(String ruta1, String ruta2) {

        try (BufferedReader bufferReader1 = new BufferedReader(new FileReader(ruta1));
        		
             BufferedReader bufferReader2 = new BufferedReader(new FileReader(ruta2))) {

            String textoLinea1;
            
            String textoLinea2;
            
            int numeroLinea = 1;
            
            boolean archivosIguales = true;

        
            
            while ((textoLinea1 = bufferReader1.readLine()) != null && (textoLinea2 = bufferReader2.readLine()) != null) {

                if (!textoLinea1.equals(textoLinea2)) {
                    
                    int columnaDiferencia = buscarIndiceDiferencia(textoLinea1, textoLinea2);

                    System.out.println("Los archivos son DISTINTOS.");
                    System.out.println("Diferencia encontrada en:");
                    System.out.println("- Línea: " + numeroLinea);
                    System.out.println("- Carácter: " + (columnaDiferencia + 1));
                    
                    archivosIguales = false;
                    
                    break;
                } 

                numeroLinea++;
            } 

            if (archivosIguales) {
                
                if (bufferReader1.readLine() != null || bufferReader2.readLine() != null) {
                	
                    System.out.println("Los archivos son DISTINTOS (uno es más largo que el otro).");
                    
                } else {
                	
                    System.out.println("Los archivos son EXACTAMENTE IGUALES.");
                    
                }
            }

        } catch (IOException e) {
            System.out.println("Error al acceder a los archivos: " + e.getMessage());
        }
    }

    public static int buscarIndiceDiferencia(String cadena1, String cadena2) {
        int indice = 0;

        while (indice < cadena1.length() && indice < cadena2.length()) {
            
            if (cadena1.charAt(indice) != cadena2.charAt(indice)) {
                return indice;
            }
            
            indice++;
        }

        return indice;
    }
}