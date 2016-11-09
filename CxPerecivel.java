import java.time.*;
import java.time.temporal.ChronoUnit;

public class CxPerecivel extends Caixa{
    private LocalDate dataValidade;
    
    public CxPerecivel(String umId,int umPeso,LocalDate umaDataVal){
        super(umId,umPeso);
        dataValidade = umaDataVal;
    }
       
    public LocalDate getDataValidade(){
        return(dataValidade);
    }
    
    public boolean naValidade(LocalDate hoje){
        if (dataValidade.compareTo(hoje) <= 0){
            return(true);
        }else{
            return(false);
        }
    }
    public int getPesoMax(){
        return(50);
    }
    
    public double getCusto(){
        return(500);
    }
}

