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
public class EmTransito extends Observable {

    private static EmTransito instance = null;
    private List<Veiculo> veiculos;
    private int cont = 0;
    private Veiculo v = new VeiculoMedio("TT", "adeas");
    private List<Veiculo> veiculosComTempo;

    private EmTransito() {
        veiculos = new ArrayList<>();
        veiculos.add(v);
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
            veiculos.remove(v);
            return true;
        } else {
            return false;
        }
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
