/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatf;

/**
 *
 * @author lucas
 */
public abstract class Veiculo {
    private String placa;
    private String destino;
    
    public int tempoViagem(int distancia){
        int dias = (int)((distancia / getVelMedia())/8);  
        if(dias <= 0){
            dias = 1;
        }
        return dias;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + "|   Destino: " + destino;
    }
    
    public double custoViagem(int distancia, double gasolina){
        return ((distancia/getConsumo())*gasolina)*1.2;
    }

    public Veiculo(String placa, String destino) {
        this.placa = placa;    
        this.destino = destino;
    }

    public String getPlaca() {
        return placa;
    }

    public String getDestino() {
        return destino;
    }

    public abstract double getConsumo();

    public abstract double getAutonomia();

    public abstract double getVelMedia(); 
    
    public abstract double getCapacidadeMax();    
    
}
