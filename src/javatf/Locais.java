package javatf;
import java.util.*;

public class Locais{
    private static Locais lstLoc = new Locais();
    private List<Local> locais;
    
    private Locais(){
        locais = new ArrayList<Local>(5);
        locais.add(new Local("Florianopolis",480));
        locais.add(new Local("Curitiba",750));
        locais.add(new Local("Sao Paulo",1143));
        locais.add(new Local("Rio de Janeiro",1569));
        locais.add(new Local("Campo Grande",1418));  
    }
    
    public static Locais getInstance(){
        return(lstLoc);
    }
    
    public Local getLocal(int i){
        return(locais.get(i));
    }
    
    public int qtdadeLocais(){
        return(locais.size());
    }
}
