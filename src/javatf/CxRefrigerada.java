package javatf;
public class CxRefrigerada extends Caixa{
    private boolean energia;
        
    public CxRefrigerada(String umId,int umPeso){
        super(umId,umPeso);
        energia = false;
    }
        
    public void ligaEnergia(){
        energia = true;
    }
    
    public void desligaEnergia(){
        energia = false;
    }
    
    public boolean ligado(){
        return(energia);
    }
    
    @Override
    public int getPesoMax(){
        return(100);
    }
    
    @Override
    public double getCusto(){
        return(750);
    }
}

