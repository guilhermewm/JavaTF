public class Local{
    private String cidade;
    private int distancia;
    
    public Local(String umaCidade,int umaDistanciaKm){
        cidade = umaCidade;
        distancia = umaDistanciaKm;
    }
    
    public String getCidade(){
        return(cidade);
    }
    
    public int getDistancia(){
        return(distancia);
    }
}
