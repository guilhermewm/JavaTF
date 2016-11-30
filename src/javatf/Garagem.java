package javatf;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author 16111019
 */
public class Garagem extends Observable {

    private static Garagem instance = new Garagem();

    private List<Veiculo> veiculos;
    private int cont = 0;
    private Veiculo c1 = new VeiculoPequeno("AAA-9975", "Canoas");
    private Veiculo c2 = new VeiculoMedio("BBB-9975", "Canoas");
    private Veiculo c3 = new VeiculoGrande("CCC-9975", "Canoas");
    
    private Veiculo cr1 = new VeiculoPequeno("DDD-9975", "Curitiba");
    private Veiculo cr2 = new VeiculoMedio("EEE-9975", "Curitiba");
    private Veiculo cr3 = new VeiculoGrande("FFF-9975", "Curitiba");
    
    private Veiculo sp1 = new VeiculoPequeno("GGG-9975", "São Paulo");
    private Veiculo sp2 = new VeiculoMedio("HHH-9975", "São Paulo");
    private Veiculo sp3 = new VeiculoGrande("III-9975", "São Paulo");
    
    private Veiculo rj1 = new VeiculoPequeno("JJJ-9975", "Rio de Janeiro");
    private Veiculo rj2 = new VeiculoMedio("KKK-9975", "Rio de Janeiro");
    private Veiculo rj3 = new VeiculoGrande("LLL-9975", "Rio de Janeiro");
   
    private Veiculo cg1 = new VeiculoPequeno("MMM-9975", "Campo Grande");
    private Veiculo cg2 = new VeiculoMedio("NNN-9975", "Campo Grande");
    private Veiculo cg3 = new VeiculoGrande("OOO-9975", "Campo Grande");

    private Garagem() {
        veiculos = new ArrayList<>();
        veiculos.add(c1);
        veiculos.add(c2);
        veiculos.add(c3);
        
        veiculos.add(cr1);
        veiculos.add(cr2);
        veiculos.add(cr3);
        
        veiculos.add(sp1);
        veiculos.add(sp2);
        veiculos.add(sp3);
        
        veiculos.add(rj1);
        veiculos.add(rj2);
        veiculos.add(rj3);
        
        veiculos.add(cg1);
        veiculos.add(cg2);
        veiculos.add(cg3);
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public List<Veiculo> getVeiculosByDestino(String destino) {

        List<Veiculo> lv = new ArrayList<>();
        for (int x = 0; x < veiculos.size(); x++) {
            if (veiculos.get(x).getDestino().equals(destino)) {
                lv.add(veiculos.get(x));
            }
        }

        return lv;
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

    public Boolean estaciona(Veiculo veiculo) {
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

    @Override
    public String toString() {
        return "" + veiculos.get(cont);
    }

    public static Garagem getInstance() {
        return (instance);
    }

}
