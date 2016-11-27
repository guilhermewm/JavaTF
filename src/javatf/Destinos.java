package javatf;
import java.util.*;

public class Destinos{
    private static Destinos lstLoc = new Destinos();
    private List<Local> locais;
    
    private Destinos(){
        locais = new ArrayList<Local>(5);
        locais.add(new Local("Canoas",480));
        locais.add(new Local("Curitiba",750));
        locais.add(new Local("São Paulo",1143));
        locais.add(new Local("Rio de Janeiro",1569));
        locais.add(new Local("Campo Grande",1418));  
    }
    
    public static Destinos getInstance(){
        return(lstLoc);
    }
    
    public List<Local> getDestinos(){
        return locais;
    }
    
    public int getDistancia(String cidade){
        int a = 0;
        for(int x = 0; x < locais.size(); x++){
            if(locais.get(x).getCidade() == cidade){
                a = locais.get(x).getDistancia();
            }
        }
        return a;
    }   
    
    public Local getLocal(int i){
        return(locais.get(i));
    }
    
    public int qtdadeLocais(){
        return(locais.size());
    }
}
