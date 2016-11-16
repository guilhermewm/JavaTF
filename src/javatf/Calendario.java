/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatf;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author gwm13
 */
public class Calendario {    
    LocalDate date = LocalDate.of(2016, 11, 16); 
            
    public void nextDay(){
        date = date.plusDays(1);
    }
    
    public LocalDate getDate(){
        return this.date;
    }
    
}
