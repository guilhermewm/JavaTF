/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatf;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author gwm13
 */
public class Calendario {
    Calendar c = Calendar.getInstance();
  
    //Método que pula um dia no calendário
    public void nextDay(){
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day+1);
    }
    
    //Método que volta um dia no calendário
    public void beforeDay(){
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day-1);
    }
    
    //Método que retorna o dia/mes/ano em string
    public String date(){
        return c.get(Calendar.DAY_OF_MONTH)+ "/"+ c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
    }

    
    
    
    
}
