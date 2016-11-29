package javatf;

import java.time.LocalDate;

/**
 *
 * @author gwm13
 */
public class Calendario {

    private static Calendario instance = new Calendario();
    LocalDate date = LocalDate.of(2016, 11, 16);

    private Calendario() {
    }

    public void nextDay() {
        date = date.plusDays(1);
    }
    
    public LocalDate novoDia(LocalDate d){
        return d.plusDays(1);
    }
    
    public LocalDate diaAnterior(LocalDate d){
        return d.plusDays(-1);
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "" + date;
    }

    public static Calendario getInstance() {
        return (instance);
    }
}
