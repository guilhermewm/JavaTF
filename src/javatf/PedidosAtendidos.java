package javatf;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

public class PedidosAtendidos {
    private static PedidosAtendidos instance = new PedidosAtendidos();
    private TreeMap<LocalDate, Integer> atendidos = new TreeMap<>();
    
    private PedidosAtendidos() {
    }
    
    public void criaGrafico(LocalDate hoje, List<Pedido> pedido ){
        int count = 0;
        
        for(int i=0;i<pedido.size();i++) {            
                count++;
        }
        if(count > 0) {
            atendidos.put(hoje, count);
        }
    }
    
    public static PedidosAtendidos getInstance() {
        return (instance);
    }

    public TreeMap<LocalDate, Integer> getAtendidos() {
        return atendidos;
    }    
}
