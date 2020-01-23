package repaso2examenbimestral2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

public class Repaso2examenBimestral2 {

    public static void main(String[] args) {
        Random numero_aleatorio = new Random();
        String linea;
        double eva_parcial1;
        double eva_parcial2;
        double trabajo_1;
        double trabajo_2;
        double suma_1_parcial;
        double suma_2_parcial;
        double total_bimestre;
        double total_bimestre_extra;
        try {
            Scanner inFile = new Scanner(new File("Base.csv"));
            int notaa_fin1 = numero_aleatorio.nextInt(20);
            int notaa_fin2 = numero_aleatorio.nextInt(20);
            linea = inFile.nextLine();
            Formatter outFile = new Formatter("BaseFinal.csv");
            outFile.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", "Nombres", "Foro1", "Chat1", "Video1", "Trabajo1", "Evalu_1", "Foro2", "Chat2", "Video2", "Trabajo2", "Evalu_2", "Final1", "Final2", "TOTAL", "ALERTA", "PROMOCION");
            while (inFile.hasNext()) {
                linea = inFile.nextLine();
                String[] tokens = linea.split(";");
                //alamcena notas las columnas y las envia al archivo de salida
                outFile.format("%s;", tokens[0]);
                outFile.format("%s;", tokens[1]);
                outFile.format("%s;", tokens[2]);
                outFile.format("%s;", tokens[3]);
                outFile.format("%s;", tokens[4]);
                outFile.format("%s;", tokens[5]);
                outFile.format("%s;", tokens[6]);
                outFile.format("%s;", tokens[7]);
                outFile.format("%s;", tokens[8]);
                outFile.format("%s;", tokens[9]);
                outFile.format("%s;", tokens[10]);
                //Transormar de string a double
                trabajo_1 = Double.parseDouble(tokens[4]);
                trabajo_2 = Double.parseDouble(tokens[9]);
                eva_parcial1 = Double.parseDouble(tokens[5]);
                eva_parcial2 = Double.parseDouble(tokens[10]);
                //suma  de las notas de primer y segundo parcial
                suma_1_parcial = Double.parseDouble(tokens[1])
                        + Double.parseDouble(tokens[2])
                        + Double.parseDouble(tokens[3])
                        + Double.parseDouble(tokens[4])
                        + Double.parseDouble(tokens[5]);
                suma_2_parcial = Double.parseDouble(tokens[6])
                        + Double.parseDouble(tokens[7])
                        + Double.parseDouble(tokens[8])
                        + Double.parseDouble(tokens[9])
                        + Double.parseDouble(tokens[10]);

                if (eva_parcial1 < 8 && eva_parcial2 < 8) {//si saca menos de 8 en las dos va a supletorio
                    outFile.format("%d;", notaa_fin1);
                    outFile.format("%d;", notaa_fin2);
                    outFile.format("%.2f;", total_bimestre = notaa_fin1 + notaa_fin2);
                    outFile.format("%s;", "Rendir Supletorios");
                    if (total_bimestre >= 28) {
                        outFile.format("%s;", "Aprobado");
                    } else {
                        outFile.format("%s;", "Reprobado");
                    }

                } else if (eva_parcial1 < 8) { // si saca menos de 8 en el primer parcial da el supleotrio 1
                    outFile.format("%d;", notaa_fin1);
                    outFile.format("%s;", " ");
                    outFile.format("%.2f;", total_bimestre = notaa_fin1 + suma_2_parcial);
                    outFile.format("%s;", "Rendir Final 1");

                    if (total_bimestre >= 28) {
                        outFile.format("%s;", "Aprobado");
                    } else {
                        outFile.format("%s;", "Reprobado");
                    }
                } else if (eva_parcial2 < 8) {//Si saca menos de 8 en el segundo parcial le toca dar el supletorio 2
                    outFile.format("%s;", " ");
                    outFile.format("%d;", notaa_fin2);
                    outFile.format("%.2f;", total_bimestre = suma_1_parcial + notaa_fin2);
                    outFile.format("%s;", "Rendir Final 2");
                    if (total_bimestre >= 28) {
                        outFile.format("%s;", "Aprobado");
                    } else {
                        outFile.format("%s;", "Reprobado");
                    }
                } else if (eva_parcial1 >= 8 && eva_parcial2 >= 8) {
                    total_bimestre_extra = suma_1_parcial + suma_2_parcial;
                    if (total_bimestre_extra < 28) {
                        /*Si saca  8 o mas en las dos evaluaciones preseciales pero aun asi
                         no logra sacar los 28 puntos ; se realiza lo siguiente:
                         1.Opcion= Si el primer parcial es menor que el segundo se rinde el examen final de este parcial
                         2.Opcion=Si el segundo  parcial es menor que el primero se rinde el examen final de este parcial
                         */
                        if (suma_1_parcial < suma_2_parcial) {
                            //Opcion 1
                            outFile.format("%d;", notaa_fin1);
                            outFile.format("%s;", " ");
                            outFile.format("%.2f;", total_bimestre = notaa_fin1 + suma_2_parcial);
                            outFile.format("%s;", "Rendir Final 1");
                            if (total_bimestre >= 28) {
                                outFile.format("%s;", "Aprobado");
                            } else {
                                outFile.format("%s;", "Reprobado");
                            }
                        } else {
                            //Opcion 2
                            outFile.format("%s;", " ");
                            outFile.format("%d;", notaa_fin2);
                            outFile.format("%.2f;", total_bimestre = notaa_fin2 + suma_1_parcial);
                            outFile.format("%s;", "Rendir Final 2");
                            if (total_bimestre >= 28) {
                                outFile.format("%s;", "Aprobado");
                            } else {
                                outFile.format("%s;", "Reprobado");
                            }

                        }

                    } else {
                        /*Si saca  8 o mas en las evaluaciones parciales y el promedio es  mayor 
                         o igual a 28 automaticamente esta aprobado
                         */
                        outFile.format("%s;", " ");
                        outFile.format("%s;", " ");
                        outFile.format("%.2f;", total_bimestre = total_bimestre_extra);
                        outFile.format("%s;", " ");
                        outFile.format("%s;", "Aprobado");

                    }
                } else if ((suma_1_parcial < 14 && suma_2_parcial < 14)
                        && (suma_1_parcial == suma_2_parcial) && (eva_parcial1 >= 8
                        && eva_parcial2 >= 8)) {
                    /*Si el promedio de cada parcial son iguales aunque sean menores de 14 y las evaluaciones presenciales son mayores o igual a 8  
                     el estudiante tendra que rendir el examen del segundo parcial*/
                    outFile.format("%s;", "");
                    outFile.format("%d;", notaa_fin2);
                    outFile.format("%.2f;", total_bimestre = suma_1_parcial + notaa_fin2);
                    outFile.format("%s;", "Rendir Final  2");
                    if (total_bimestre >= 28) {
                        outFile.format("%s;", "Aprobado");
                    } else {
                        outFile.format("%s;", "Reprobado");

                    }
                }
                outFile.format("%s", "\n");// Sirve para dar un espacio de linea
            }
            inFile.close(); // Cierra la lectura del archivo Base.cvs
            outFile.close();// Cierra la escritura del archivo BaseFinal.cvs

        } catch (FileNotFoundException e) {
            System.err.printf("\nExcepcion: %s\n", e);
            System.out.println("Error con la ubicacion del archivo base ");
        }
    }

}
