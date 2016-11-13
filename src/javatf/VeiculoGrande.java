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
public class VeiculoGrande extends Veiculo {
    
    public VeiculoGrande(String placa,String destino) {
        super(placa,destino);
    }

    @Override
    public double getAutonomia() {
        return (4000); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public double getVelMedia() {
        return (70);
    }
    
    @Override
    public double getConsumo() {
        return 2;
    }
    
}
