package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class Files {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Formatter outArchivo = new Formatter("username_Notas.csv");
        outArchivo.format("Materia;Nota 1er.Bim;Nota 2er.Bim\n");
        outArchivo.format("Programacion de Algoritmo; %d; %d\n", 10, 20);
        outArchivo.format("Calculo;%d ;%d\n", 14, 13);
        outArchivo.format("Fisica;%d ;%d\n", 10, 17);
        outArchivo.format("Matematicas;%d;%d", 10, 11);
        outArchivo.close();

        Scanner inArchivo = new Scanner(new File("username_Notas.csv"));
        outArchivo = new Formatter("username_NotasResultados.csv");

        int conta = 0;
        double promedio = 0;
        double promedio2 = 0;
        while (inArchivo.hasNext()) {
            String tokens[] = inArchivo.nextLine().split(";");
            int i = 1;

            for (String nota : tokens) { // recorre el archivo en consola 

                System.out.printf("%s%n", nota);

                //Transforma la nota de string a double.
                //double notaAux = Double.valueOf(nota);
                //System.out.println("Nota [" + i + "]: " + notaAux);
                i++;
            }

            if (conta != 0) {
                String materia = tokens[0];
                double notaAux = Double.parseDouble(tokens[1].replace(",", "."));
                double notaAux2 = Double.parseDouble(tokens[2].replace(",", "."));
                double sumatoria = notaAux + notaAux2;
                String estado = "Reprobado";

                if (sumatoria >= 28) {
                    estado = "Aprobado";
                }

                promedio += notaAux;
                promedio2 += notaAux2;

                String StringFormat = String.format("%s;%.2f;%.2f;%.2f;%.2f;%s\n", materia, notaAux, notaAux2, sumatoria, sumatoria / 2, estado);
                System.out.printf("%s %.2f +  %.2f  = %.2f || Promedio : %.2f || %s\n", materia, notaAux, notaAux2, sumatoria, sumatoria / 2, estado);
                System.out.println("");
                outArchivo.format("%s", StringFormat);
            } else {
                outArchivo.format("%s;%s;%s;Total;Promedio;Estado\n", tokens[0], tokens[1], tokens[2]);

            }
            conta++;
            System.out.println("/////////////////////////");
        }

        inArchivo.close();

        promedio /= 4;
        promedio2 /= 4;

        outArchivo.format("Promedio  1er.Bim: %.2f\n ", promedio);
        System.out.printf("Promedio  1er.Bim:%.2f\n ", promedio);
        System.out.println("");
        outArchivo.format("Promedio  2dor.Bim: %.2f\n ", promedio2);
        System.out.printf("Promedio  2dor.Bim: %.2f\n ", promedio2);

        outArchivo.close();

    }

}
