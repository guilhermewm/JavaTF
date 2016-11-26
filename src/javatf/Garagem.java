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
public class Garagem extends Observable{
    private static Garagem instance = new Garagem();    
    private List<String> veiculos;
    private int cont = 0;

    private Garagem() {
        veiculos = new ArrayList<>();
    }
    
    public List<String> getVeiculos(){
        return veiculos;
    } 
        
    public Boolean estaciona(Veiculo veiculo){
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
        return "" + veiculos.get(cont-1);
    }
    
    public static Garagem getInstance(){        
        return (instance);
    }
    
    
    
}
