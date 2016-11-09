package javatf;
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Pedido implements Iterable<Caixa>{
    private static int nroPed = 0;
    private String id;
    private LocalDate dataPrev;
    private LocalDate dataEntrega;
    private Local local;
    private List<Caixa> caixas;
    
    public Pedido(LocalDate umaData,Local umLocal){
        nroPed++;
        id = "Ped"+nroPed;
        dataPrev = umaData;
        dataEntrega = null;
        local = umLocal;
        caixas = new LinkedList<Caixa>();
    }
    
    public String getId(){
        return(id);
    }
    
    public LocalDate getDataPrevista(){
        return(dataPrev);
    }

    public LocalDate getDataEntrega(){
        return(dataEntrega);
    }
    
    public boolean entregue(){
        return(dataEntrega != null);
    }
    
    public void entregueEm(LocalDate data){
        dataEntrega = data;
    }
    
    public Local getLocal(){
        return(local);
    }
    
    @Override
    public Iterator<Caixa> iterator(){
        return(caixas.iterator());
    }
    
    public void addCaixa(Caixa cx){
        caixas.add(cx);
    }
    
    public int qtdadeCaixas(){
        return(caixas.size());
    }
    
    public int qtdadeCaixasTipo(TipoCaixa tx){
        int cont = 0;
        for(Caixa c:caixas){
          switch(tx){
              case NORMAL:
                  if (c instanceof CxNormal){
                      cont++;
                  }
                  break;
              case REFRIGERADA:
                  if (c instanceof CxRefrigerada){
                      cont++;
                  }
                  break;
              case PERECIVEL:
                  if (c instanceof CxPerecivel){
                      cont++;
                  }
                  break;
          }
        }
        return(cont);
    }              
}
    
