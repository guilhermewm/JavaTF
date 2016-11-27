package javatf;

/**
 *
 * @author lucas
 */
public class VeiculoGrande extends Veiculo {

    public VeiculoGrande(String placa, String destino) {
        super(placa, destino);
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
        return (2);
    }

    public double getCapacidadeMax() {
        return (5000);
    }

    @Override
    public int getTomadas() {
        return(9);
    }

}
