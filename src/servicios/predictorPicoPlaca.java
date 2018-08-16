/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author root
 */


public class predictorPicoPlaca {
    
    //En caso de formato erroneo retorna -1
    public static int diaFecha (String fec){
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat dateFor  = new SimpleDateFormat("dd/MM/yyyy");
                
        try{ 
            Date fecha=dateFor.parse(fec);
            cal.setTime(fecha);
        return cal.get(Calendar.DAY_OF_WEEK)-1;
        }
        catch (ParseException e) {
            return -1;
        }                
    }
    //Uiltimo digito de placa en caso de no ser un numero retorna -1
    public static int ultimoDigito (String [] plac){
        int digito;
        try {
            digito= parseInt(plac[plac.length-1]);            
        }
        catch (NumberFormatException e){
            return -1;
        }   
        return digito;
    }
    
     //Devuelve true si el string cumple formato hh:mm
    public static boolean validaFormatoHora (String hor){
        try {
            LocalTime horaPico =LocalTime.parse(hor);
            return true;
        }
        catch (DateTimeParseException e){
            return false;
        }        
    }
    
    //Devuelve true si se encuentra en el rango de horas
    public static boolean validarRangHora (LocalTime hora, LocalTime iniUno, 
            LocalTime finUno, LocalTime iniDos,LocalTime finDos){
        
        return ((hora.isAfter(iniUno)&& hora.isBefore(finUno)) || 
                (hora.isAfter(iniDos)&& hora.isBefore(finDos)));
    }
    
    //Retorna mensaje indicado si el vehiculo puede circular
    public static String validador (String [] placa, String fecha, String hora){
        
        String si= "El vehículo PUEDE circular.";
        String no= "El vehículo NO PUEDE circular.";
        int digito=ultimoDigito(placa);
        LocalTime horaPico=LocalTime.parse(hora);
        LocalTime iniManana= LocalTime.of(7,00);
        LocalTime finManana= LocalTime.of(9,30);
        LocalTime iniTarde= LocalTime.of(16,00);
        LocalTime finTarde= LocalTime.of(19,30);
        
        boolean rango =validarRangHora(horaPico, iniManana, finManana, iniTarde, finTarde);
               
        switch (diaFecha(fecha)){
            case 1:
                if ((digito==1 || digito ==2) && rango) return no;
                break;
            case 2:
                if ((digito==3 || digito ==4) && rango) return no;
                break;
            case 3:
                if ((digito==5 || digito ==6) && rango) return no;
                break;
            case 4:
                if ((digito==7 || digito ==8) && rango) return no;
                break;
            case 5:
                if ((digito==9 || digito ==0) && rango) return no;
                break;
        }
                
        return si;
    }
}
