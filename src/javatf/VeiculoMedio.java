package javatf;

/**
 *
 * @author lucas
 */
public class VeiculoMedio extends Veiculo {

    public VeiculoMedio(String placa, String destino) {
        super(placa, destino);
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
        return (3);
    }

    public double getCapacidadeMax() {
        return (2500);
    }

    @Override
    public int getTomadas() {
        return (6);
    }
}
