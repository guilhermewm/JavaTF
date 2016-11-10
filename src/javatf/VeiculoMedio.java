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
public class VeiculoMedio extends Veiculo {
    
    public VeiculoMedio(String placa,String destino) {
        super(placa,destino);
    }
    
    @Override
    public double getAutonomia() {
        return (1000); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getVelMedia() {
        return (80);
    }
    
    @Override
    public double getConsumo() {
        return 3;
    }
}
