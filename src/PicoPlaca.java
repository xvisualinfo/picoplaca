
import java.util.Scanner;
import servicios.predictorPicoPlaca;
/**
 *
 * @author root
 */


public class PicoPlaca {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner placaConsola = new Scanner(System.in);
        Scanner fechaConsola = new Scanner(System.in);
        Scanner horaConsola = new Scanner(System.in);
        String[] placa;
        String fecha;
        String hora;
        
        do {            
            System.out.print("Ingrese la placa: ");
            placa= placaConsola.next().split("");
            if ((predictorPicoPlaca.ultimoDigito(placa)<0) || (placa.length<7))
                System.out.println("Placa debe tener al menos 7 caracteres "
                        + "y último dígito numérico...");
        } while ((predictorPicoPlaca.ultimoDigito(placa)<0) || (placa.length<7));
        
        do{
            System.out.print("Ingrese la fecha (dd/mm/aaaa): ");
            fecha = fechaConsola.next();
            if (predictorPicoPlaca.diaFecha(fecha)<0) System.out.println("Error "
                    + "en el formato de fecha...");
        } while(predictorPicoPlaca.diaFecha(fecha)<0);
                        
        do{
            System.out.print("Ingrese la hora (hh:mm): ");
            hora = horaConsola.next();
            if (!predictorPicoPlaca.validaFormatoHora(hora)) System.out.println("Error"
                    + " en el formato de hora...");
        } while (!predictorPicoPlaca.validaFormatoHora(hora));               
        System.out.println(predictorPicoPlaca.validador(placa, fecha, hora));
    }
    
}
