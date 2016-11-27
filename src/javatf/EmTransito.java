/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatf;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author 16111019
 */
public class EmTransito extends Observable{
    private static EmTransito instance = null;    
    private List<String> veiculos;
    private int cont = 0;
    private Veiculo v = new VeiculoMedio("TT", "adeas");
    private List<String> veiculosComTempo;

    private EmTransito() {
        veiculos = new ArrayList<>();
        veiculos.add(v.toString());
    }
    
    public List getVeiculos(){
        System.out.println("Veiculos em Transito: " + veiculos);
        return veiculos;
    } 
    
    public Boolean viajem(Veiculo veiculo){
        if(veiculo != null){
            cont++;
            veiculos.add(veiculo.toString());
            setChanged();
            notifyObservers(veiculo);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "" + veiculos.get(cont);
    }
    
    
    
    public static EmTransito getInstance(){
        if (instance == null ){
            instance = new EmTransito();    
        }
        return (instance);
    }
}
