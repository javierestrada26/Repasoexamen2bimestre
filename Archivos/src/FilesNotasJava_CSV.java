/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author utpl
 */
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Locale;

public class FilesNotasJava_CSV {
    public static void main(String[] args) {
        try {
            //FLUJO DE SALIDA al archivo,
            //con Charset US-ASCII, para escribir . en lugar de ,
            Locale ingles = new Locale("en", "EN");
            Formatter outArchivo = new Formatter("notas.csv", "US-ASCII", ingles);
            outArchivo.format("%d;%d;%d;%n", 4, 20, 8);
            outArchivo.format("%d;%d;%d;%n", 15, 19, 16);
            outArchivo.close();

            //FLUJO DE ENTRADA del archivo.
            Scanner inArchivo = new Scanner(new File("notas.csv"));
            Formatter outResultados = new Formatter("resultados.csv");
            String contenido;
            int nroEst = 1;
            int promedio = 0;
            int suma = 0;
            while (inArchivo.hasNext()) {
                System.out.println("NOTAS DEL ESTUDIANTE: " + nroEst);
                contenido = inArchivo.nextLine();
                String[] tokens = contenido.split(";");
                //System.out.println(tokens[0] + ", " + tokens[1] + ", " + tokens[2]);
                System.out.printf("%-5s%-5s%-5s%n", tokens[0], tokens[1], tokens[2]);
                int i = 1;
                int suma2 = 0;
                
                for (String nota : tokens) {
                    int numero = Integer.parseInt(nota);
                    suma2 = suma2 + numero;
                    suma = suma + numero;
                    System.out.println("Nota [" + i + "]: " + numero);
                    outResultados.format("%d;", numero);
                    //Transforma la nota de string a double.
                    //double notaAux = Double.valueOf(nota);
                    //System.out.println("Nota [" + i + "]: " + notaAux);
                    i++;
                }
                outResultados.format("%d; %n", suma2);
                System.out.println("suma " + suma2);
                nroEst++;
                
            }
            promedio = suma / 2;
            System.out.println("promedio " + promedio);
            outResultados.format("promedio; " + promedio);
            inArchivo.close();
            outResultados.close();
        } catch (NumberFormatException numberFormatException) {
            System.err.println("Formato de numero invalido");
        } catch (Exception e) {
            System.err.println("Excepcion generada: " + e);
            System.exit(1); //Termina el programa.
        }
    }
}
// crear archivo csv, 
