/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatf;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 16111019
 */
public class Garagem {
    private static Garagem instance = null;    
    private List<Veiculo> veiculos;

    private Garagem() {
        veiculos = new ArrayList<>();
    }
    
    public List<Veiculo> getVeiculos(){
        return veiculos;
    } 
        
    public Boolean estaciona(Veiculo veiculo){
        if(veiculo != null){
            veiculos.add(veiculo);
            return true;
        }else{
            return false;
        }
    }
    
    public static Garagem getInstance(){
        if (instance == null ){
            instance = new Garagem();    
        }
        return (instance);
    }
    
    
    
}
