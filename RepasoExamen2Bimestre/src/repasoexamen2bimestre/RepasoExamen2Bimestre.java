package repasoexamen2bimestre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Formatter;
import java.util.Scanner;

public class RepasoExamen2Bimestre {

    public static void main(String[] args) throws FileNotFoundException {
        
        String linea;
        String lineas;
        String estado;
        String porcen = "%";
        double sumatoria_notas = 0;
        double promedio_curso = 0;
        double promedio_apro = 0;
        double promedio_repro = 0;
        char primer_caracter;
        char segundo_caracter;
        int c_estudiantes = 0;
        int c_aprobados = 0;
        int cest_superaprom = 0;
        char a[] = new char[2];
       
            Scanner inFile = new Scanner(new File("listadoIn.csv"));
            linea = inFile.nextLine();//empipeza a leer desde la segud liena el archivo listados
            Formatter outFile = new Formatter("salidaReportes.csv");
            outFile.format("%s;%s\n", "Mail", "Texto");//se agregan lienas con este comando
            while (inFile.hasNext()) {//con esto se lee todo el archivo listado 
                c_estudiantes += 1;// cuenta los estudiantes que existen 
                linea = inFile.nextLine();// para que lea desde la segunda linea
                String[] tokens = linea.split(";");//recorre los elementos separados por un ;
                String aux = tokens[2]; //esto almacena la segunda columna 
                String[] aux2 = aux.split(" ");//recorre eementos que tengan un ""
                a[0] = aux2[2].charAt(0);//con estos dos arrays guardo los nombres 
                a[1] = aux2[3].charAt(0);

                outFile.format("%s%s%s@utpl.edu.ec", a[0], a[1], aux2[0]); // Presenta en el archivo saliente salidaReportes en la columna 0  el email creado.
                int nota = Integer.parseInt(tokens[3]); //convierte las notas de la columna tres en enteros
                if (nota >= 28) { //con este ciclo vemos si laa nota es aprobada o rprobada
                    estado = "Aprobado";
                    c_aprobados = c_aprobados + 1;
                } else {
                    estado = "Reprobado";
                }

                outFile.format(" Querida %s %s la UTPL le notifica que Ud. a sido   %s  en la Materia X con la nota de  %s ."
                        + "Para mayor informacion comuniquese con su docente al rjestrada2@utpl.edu.ec\n", aux2[2], aux2[0], estado, nota);
                sumatoria_notas += nota;//aqui se almacena todas las notas de los estudiantes
                promedio_curso = sumatoria_notas / c_estudiantes; //sacamos el promedio del curso
                promedio_apro = (c_aprobados * 100.00) / c_estudiantes; //promedio de aprobados  todos los aprobados  * 100 que es el porcentaje total y dividio para el total de estudiantes
                promedio_repro = (100 - promedio_apro);//promedio de reprobados
            } //fin while
            System.out.println("\n**Infrome de Archivo de salida**\n");
            System.out.printf("Porcentaje de estudiantes Aprobados:%.2f%s\n", promedio_apro, porcen);
            System.out.printf("Porcentaje de estudiantes Repdrobados: %.2f%s\n", promedio_repro, porcen);
            inFile.close(); //se cierra el archivo lsitados
            
            Scanner File = new Scanner(new File("listadoIn.csv"));// se vuelve abrir el archivo
            lineas = File.nextLine();//para que empiece a ller desde la segunda linea
            System.out.printf("\nLos estudiantes que superan el promedio del curso <%.2f>son :\n\n", promedio_curso);
            while (File.hasNext()) {
                lineas = File.nextLine();
                String[] tokens = lineas.split(";");
                int nota = Integer.parseInt(tokens[3]);
                if (nota >= promedio_curso) { //es para vers si la nota es mayor a 28 aprueba sino reprueba
                    cest_superaprom += 1;// cuenta los estudiantes que aprobaron

                    System.out.printf("%s con la nota de %s.\n", tokens[2], nota);
                }
            }
            System.out.printf("\nTotal %s estudiantes superaron el promedio del curso de (%.2f)\n", cest_superaprom, promedio_curso);
            outFile.close();
        
    }

}
