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
    private Veiculo v2 = new VeiculoGrande("ABC-9975", "Canoas");
    private Veiculo v3 = new VeiculoGrande("TGH-9975", "São Paulo");
    private Veiculo v4 = new VeiculoGrande("JKL-9975", "Curitiba");    
    private Veiculo v5 = new VeiculoGrande("AFA-9975", "Campo Grande");
    private Veiculo v6 = new VeiculoGrande("BBB-9975", "Rio de Janeiro");    
    private Veiculo v7 = new VeiculoGrande("BAB-9975", "Rio de Janeiro");
    
    private Garagem() {
        veiculos = new ArrayList<>();
        veiculos.add(v2.toString());
        veiculos.add(v3.toString());
        veiculos.add(v4.toString());
        veiculos.add(v5.toString());
        veiculos.add(v6.toString());
        veiculos.add(v7.toString());
    }
    
    public List<String> getVeiculos(){
        return veiculos;
    } 
    
    public List<String> getVeiculosByDestino(String destino){
        List<String> lv = new ArrayList<>();
        for(int x = 0; x < veiculos.size(); x++){
            if(veiculos.get(x).contains(destino)){
                lv.add(veiculos.get(x));
            }
        }
        return lv;
    }
    
    public Boolean removeVeiculo(String placa, String destino){        
        if(placa != null){
            for(int x = 0; x < veiculos.size(); x++){
                if(veiculos.get(x).contains(placa) && veiculos.get(x).contains(destino)){
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
