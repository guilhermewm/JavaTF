package javatf;

public abstract class Caixa {

    private String id;
    private int peso;

    public Caixa(String umId, int umPeso) {
        if (umPeso > getPesoMax()) {
            throw new ExcessoDePesoException("Excesso de peso!!");
        }
        id = umId;
        peso = umPeso;
    }

    public String getId() {
        return (id);
    }

    public int getPeso() {
        return (peso);
    }

    @Override
    public String toString() {

        return "Caixas{" + "ID: " + id + ", Peso: " + peso + '}';
    }

    public abstract int getPesoMax();

    public abstract double getCusto();
}
