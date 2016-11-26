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
    private Veiculo v = new VeiculoGrande("IKG-9975", "Porto Alegre");
    private Veiculo v2 = new VeiculoGrande("ABC-9975", "Canoas");
    private Veiculo v3 = new VeiculoGrande("TGH-9975", "Sei lá");
    private Veiculo v4 = new VeiculoGrande("JKL-9975", "Teste");
    
    private Garagem() {
        veiculos = new ArrayList<>();
        veiculos.add(v2.toString());
        veiculos.add(v3.toString());
        veiculos.add(v4.toString());
        veiculos.add(v.toString());
    }
    
    public List<String> getVeiculos(){
        System.out.println("Veiculos na Garagem: " + veiculos);
        return veiculos;
    } 
    
    public Boolean removeVeiculo(Veiculo v){        
        if(v != null){
            for(int x = 0; x < veiculos.size(); x++){
                if(veiculos.get(x).equals(v.toString())){
                    veiculos.remove(x);
                }
            }            
            return true;
        }else{
            return false;
        }
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
        return "" + veiculos.get(cont);
    }
    
    public static Garagem getInstance(){        
        return (instance);
    }
    
    
    
}
