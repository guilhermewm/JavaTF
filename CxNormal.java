public class CxNormal extends Caixa{    
    public CxNormal(String umId,int umPeso){
        super(umId,umPeso);
    }
        
    public int getPesoMax(){
        return(1000);
    }
    
    public double getCusto(){
        if (getPeso() <= 200){
            return(getPeso() * 5);
        }else{
          return(((getPeso()-200)*7.5)+(200*5));
        }
    }
}

