package javatf;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

public class PedidosAtrasados {
    private static PedidosAtrasados instance = new PedidosAtrasados();
    private TreeMap<LocalDate, Integer> atrasados = new TreeMap<>();
    
    private PedidosAtrasados() {
    }
    
    public void criaGrafico(LocalDate hoje, List<Pedido> pedido ){
        int count = 0;
        
        for(int i=0;i<pedido.size();i++) {            
            boolean verdade = pedido.get(i).getDataPrevista().isEqual(Calendario.getInstance().diaAnterior(hoje));
            if(pedido.get(i).getAtraso() && verdade) {
                count++;
            }
        }
        if(count > 0) {
            atrasados.put(hoje, count);
        }
    }
    
    public static PedidosAtrasados getInstance() {
        return (instance);
    }

    public TreeMap<LocalDate, Integer> getAtrasados() {
        return atrasados;
    }    
}
