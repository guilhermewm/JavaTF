/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatf;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author 16111019
 */

//Talvez faça o mesmo que locais


public class Destinos2 {
    private Map<String,Integer> cidades; 
    
    public Destinos2(){
        cidades = new HashMap<>();
        cidades.put("Rio de Janeiro",1500);
        cidades.put("Florianopolis",450);
        cidades.put("Curitiba",700);
        cidades.put("Canoas",20);        
        cidades.put("Pelotas",250);              
        cidades.put("Torres",190);
    }
    
    public Collection<String> getCidades(){
        return(cidades.keySet());
    }
    
    public Integer getDistancia(String cidade){
        return(cidades.get(cidade));
    }
}
    
    
    

