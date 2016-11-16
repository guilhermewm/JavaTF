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
public class EmTransito {
    private static EmTransito instance = null;    
    private List<Veiculo> veiculos;

    private EmTransito() {
        veiculos = new ArrayList<>();
    }
    
    public List getVeiculos(){
        return veiculos;
    } 
    
    public Boolean viajem(Veiculo veiculo){
        if(veiculo != null){
            veiculos.add(veiculo);
            return true;
        }else{
            return false;
        }
    }
    
    public static EmTransito getInstance(){
        if (instance == null ){
            instance = new EmTransito();    
        }
        return (instance);
    }
}
