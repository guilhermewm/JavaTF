package javatf;

import java.util.*;
import java.time.*;

public class Pedido implements Iterable<Caixa> {

    private static int nroPed = 0;
    private String id;
    private LocalDate dataPrev;
    private LocalDate dataEntrega;
    private Local local;
    private List<Caixa> caixas;
    private Veiculo veiculoEntrega;
    private Boolean atraso = false;
    private String stringAtraso = "";

    public Pedido(LocalDate umaData, Local umLocal) {
        nroPed++;
        id = "Ped" + nroPed;
        dataPrev = umaData;
        dataEntrega = null;
        local = umLocal;
        caixas = new LinkedList<Caixa>();
    }
    
    public void setAtraso(){
        atraso = true;
    }
    
    public Boolean getAtraso(){        
        return atraso;
    }
    
    public void setEntregue() {
        dataEntrega = Calendario.getInstance().getDate();
    }

    public String getId() {
        return (id);
    }
    
    public String getStringAtraso(){
        String t = "";
        if(this.atraso == false){
            t = stringAtraso = "em dia";
        }else{
            t = stringAtraso = "atrasado";
        }
        return t;
    }

    public LocalDate getDataPrevista() {
        return (dataPrev);
    }

    public LocalDate getDataEntrega() {
        return (dataEntrega);
    }

    public boolean entregue() {
        return (dataEntrega != null);
    }

    public void entregueEm(LocalDate data) {
        dataEntrega = data;
    }

    public Local getLocal() {
        return (local);
    }

    @Override
    public Iterator<Caixa> iterator() {
        return (caixas.iterator());
    }

    public void addCaixa(Caixa cx) {
        caixas.add(cx);
    }

    public int qtdadeCaixas() {
        return (caixas.size());
    }

    public double pesoTotal(){  
        double peso=0;
        for(Caixa cx :  caixas){
          peso += cx.getPeso();  
        }       
        return peso;
    }
    
    public int qtdadeCaixasTipo(TipoCaixa tx) {
        int cont = 0;
        for (Caixa c : caixas) {
            switch (tx) {
                case NORMAL:
                    if (c instanceof CxNormal) {
                        cont++;
                    }
                    break;
                case REFRIGERADA:
                    if (c instanceof CxRefrigerada) {
                        cont++;
                    }
                    break;
                case PERECIVEL:
                    if (c instanceof CxPerecivel) {
                        cont++;
                    }
                    break;
            }
        }
        return (cont);
    }
  
    
    @Override
    public String toString() {
        return "E"+this.dataEntrega+" ID: " + id + ",  Local: " + local + ", Peso Total: "+ pesoTotal()+ ", Caixas Refrigeradas: "+ qtdadeCaixasTipo(TipoCaixa.REFRIGERADA)  + " Atraso: " + getStringAtraso() + ", Caixas: " + caixas;
    }

    public void addVeiculoEntrega(Veiculo veiculo) {
        this.veiculoEntrega = veiculo;
    }
    
    public boolean foiEntregue() {        
        return this.dataEntrega != null;
    }

}
