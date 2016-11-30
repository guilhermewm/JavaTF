package javatf;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public abstract class Veiculo {

    private String placa;
    private String destino;
    private int tempoRestante;
    private List<Pedido> pedidos = null;
    private double pesoCarga;
    private int tomadas;

    public int tempoViagem(int distancia) {
        int dias = (int) ((distancia / getVelMedia()) / 8);
        if (dias <= 0) {
            dias = 1;
        }
        return dias;
    }

    public void decrementaDia() {
        tempoRestante--;
    }

    public int getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(int dias) {
        this.tempoRestante = dias;
    }

    
    @Override
    public String toString() {
        return "Placa: " + placa + ", Destino: " + destino + ", Peso restante: " + (getCapacidadeMax()-pesoCarga) + ", Tomadas restantes: " + (getTomadas()-tomadas);
    }

    public double custoViagem(int distancia, double gasolina) {
        return ((distancia / getConsumo()) * gasolina) * 1.2;
    }
    
    

    public Veiculo(String placa, String destino) {
        this.placa = placa;
        this.destino = destino;
        this.pesoCarga = 0;
    }

    public String getPlaca() {
        return placa;
    }

    public String getDestino() {
        return destino;
    }
    
    public double getPesoCarga(){
        return pesoCarga;
    }
    
    public int setPesoCarga(double peso, int t){
        if(peso+pesoCarga<=getCapacidadeMax()){            
            if(t + tomadas <= getTomadas()){
                pesoCarga += peso;
                tomadas+= t;
                return 1;
            }else{
                return 3;
            }
        }
        return 2;
    }

    public abstract double getConsumo();

    public abstract double getAutonomia();

    public abstract double getVelMedia();

    public abstract double getCapacidadeMax();
    
    public abstract int getTomadas();

    public void addPedido(Pedido itemSelecionadoPedido) {
        if (pedidos == null) {
            this.pedidos = new ArrayList<>();
        }
        pedidos.add(itemSelecionadoPedido);
    }
    
    public List<Pedido> getPedidos(){
        return this.pedidos;
    }
    
    public void limpaPedidos() {
        if(pedidos != null) {             
            pesoCarga = 0;
            tomadas = 0;
            pedidos.clear();
            pedidos = null;            
        }
    }

    public void setEntregue() {
        if(pedidos != null) { 
            for(Pedido p : pedidos) {
                p.setEntregue();
            }                   
        }
    }

}
