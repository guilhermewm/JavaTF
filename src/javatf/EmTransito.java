package javatf;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author 16111019
 */
public class EmTransito extends Observable {

    private static EmTransito instance = null;
    private List<Veiculo> veiculos;
    private int cont = 0;
    private List<Veiculo> veiculosComTempo;
    private double lucro;

    private EmTransito() {
        veiculos = new ArrayList<>();
    }

    public List getVeiculos() {
        return veiculos;
    }

    public Boolean viajem(Veiculo veiculo) {
        if (veiculo != null) {
            cont++;
            veiculos.add(veiculo);
            setChanged();
            notifyObservers(veiculo);
            return true;
        } else {
            return false;
        }
    }

    public Boolean removeVeiculo(Veiculo v) {
        if (v != null) {
            for(int i = 0; i < v.getPedidos().size(); i++){
                int qtCaixas = v.getPedidos().get(i).qtdadeCaixas();                
                double pesoTotal = v.getPedidos().get(i).pesoTotal();                
                lucro += ((pesoTotal/100)*300) - v.custoViagem(Destinos.getInstance().getDistancia(v.getDestino()), 3.2);       
            }            
            System.out.println(lucro);
            veiculos.remove(v);
            return true;
        } else {
            return false;
        }
    }

  
    
    public String getLucroString(){
        return "" + lucro;
    }

    @Override
    public String toString() {
        return "" + veiculos.get(cont);
    }

    public static EmTransito getInstance() {
        if (instance == null) {
            instance = new EmTransito();
        }
        return (instance);
    }
}
