package javatf;

/**
 *
 * @author lucas
 */
public class VeiculoPequeno extends Veiculo {

    public VeiculoPequeno(String placa, String destino) {
        super(placa, destino);
    }

    @Override
    public double getAutonomia() {
        return (1000); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getVelMedia() {
        return (90); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getConsumo() {
        return (4.5);
    }

    public double getCapacidadeMax() {
        return (1250);
    }

    @Override
    public int getTomadas() {
        return (3);
    }
}
