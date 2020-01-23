package direccionesip;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * @author SARANGO
 */
public class DireccionesIP {

    public static ArrayList<String> casoA = new ArrayList<>();
    public static ArrayList<String> casoB = new ArrayList<>();
    public static ArrayList<String> casoC = new ArrayList<>();

    public static void main(String[] args) {
        int red[] = new int[4];
        DireccionesIP ip = new DireccionesIP();
        ip.generarDirecciones(red);
        System.out.println(ip.cuantasDirecciones());
        System.out.println(ip.cuantasMenosDirecciones());
    }

    public void generarDirecciones(int[] red) {
        int limite = (int) (Math.random() * 50) + 1;
        for (int i = 0; i < limite; i++) {
            generarNumeros(red);
            if (red[0] > 0 && red[0] < 127) {
                casoA.add("Clase A " + red[0] + "." + red[1] + "." + red[2] + "." + red[3]);
                System.out.println("Clase A" + red[0] + "." + red[1] + "." + red[2] + "." + red[3]);
            } else if (red[0] > 128 && red[0] < 191) {
                casoB.add("Clase B " + red[0] + "." + red[1] + "." + red[2] + "." + red[3]);
                System.out.println("Clase B " + red[0] + "." + red[1] + "." + red[2] + "." + red[3]);
            } else if (red[0] > 192 && red[0] < 223) {
                casoC.add("Clase C " + red[0] + "." + red[1] + "." + red[2] + "." + red[3]);
                System.out.println("Clase C " + red[0] + "." + red[1] + "." + red[2] + "." + red[3]);
            }
        }
        generarArchivo();
    }

    public void generarNumeros(int red[]) {
        for (int i = 0; i < red.length; i++) {
            red[i] = (int) (Math.random() * 255);
        }
        System.out.println("IP " + red[0] + "." + red[1] + "." + red[2] + "." + red[3]);
        
    }

    public String cuantasDirecciones() {
        if (casoA.size() > casoB.size() && casoA.size() > casoC.size()) {
            return "La Clase A tiene mayor cantidades de direcciones con " + casoA.size() + "direcciones(s)";
        } else if (casoB.size() > casoC.size() && casoB.size() > casoA.size()) {
            return "La Clase B tiene mayor cantidad de direcciones con " + casoB.size() + " direccione(s)";
        } else if (casoC.size() > casoB.size() && casoC.size() > casoA.size()) {
            return "La Clase B tiene mayor cantidad de direcciones con " + casoB.size() + " direcciones(s)";
        }
        return " ";
    }

    public String cuantasMenosDirecciones() {
        if (casoA.size() < casoB.size() && casoA.size() < casoC.size()) {
            return "La Clase A tiene menor cantidades de direcciones con " + casoA.size() + "direcciones(s)";
        } else if (casoB.size() < casoC.size() && casoB.size() < casoA.size()) {
            return "La Clase B tiene menor cantidad de direcciones con " + casoB.size() + " direccione(s)";
        } else if (casoC.size() < casoB.size() && casoC.size() < casoA.size()) {
            return "La Clase C tiene menor cantidad de direcciones con " + casoB.size() + " direcciones(s)";
        }
        return "";
    }

    public void generarArchivo() {
        Formatter archivoSalida;
        try {
            archivoSalida = new Formatter("usernameIPs_Salida.csv");

            for (int i = 0; i < casoA.size(); i++) {
                archivoSalida.format("%s\r\n", casoA.get(i));
            }
            for (int i = 0; i < casoB.size(); i++) {
                archivoSalida.format("%s\r\n", casoB.get(i));
            }
            for (int i = 0; i < casoC.size(); i++) {
                archivoSalida.format("%s\r\n", casoC.get(i));
            }
             
            archivoSalida.format("%s\n\r%s", cuantasDirecciones(), cuantasMenosDirecciones());

            archivoSalida.close();
        } catch (FileNotFoundException ex) {
            System.err.print("Error al abrir archivo");
        }

    }

}
