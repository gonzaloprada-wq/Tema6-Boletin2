package Ej3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        
    	final int NUMERO_LINEAS=24;
    	
        String rutaFichero = "src/Ej3/textoLectura"; 
        
        Scanner reader = new Scanner(System.in);
        
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            
            String linea;
            int contadorLineas = 0;

            while ((linea = br.readLine()) != null) {
                
                System.out.println(linea);
                contadorLineas++;

                if (contadorLineas % NUMERO_LINEAS == 0) {
                	
                	System.out.println("\n====================================");
                    System.out.println("---------ENTER para continuar-------");
                    System.out.println("====================================");
                    reader.nextLine(); 
                }
            }
            System.out.println("\n==========");
            System.out.println("---Fin----");
            System.out.println("==========");

        } catch (IOException e) {
            System.out.println("Error--Archivo no encontrado!");
            
            System.out.println("Detalle del error: " + e.getMessage());
        } 
    }
}