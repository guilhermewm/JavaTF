package javatf;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Pedidos extends Observable {

    private static Pedidos lstPed = new Pedidos();
    private List<Pedido> pedidos;
    private int corrente;

    private Pedidos() {
        pedidos = new ArrayList<>();
        corrente = 0;
        Destinos locais = Destinos.getInstance();
        CaixaFactory cf = CaixaFactory.getInstance();

        Pedido p = new Pedido(LocalDate.of(2016, 12, 10), locais.getLocal(0));
        p.addCaixa(cf.createInstance(TipoCaixa.NORMAL, 450));
        p.addCaixa(cf.createInstance(TipoCaixa.NORMAL, 850));
        p.addCaixa(cf.createInstance(TipoCaixa.NORMAL, 940));
        p.addCaixa(cf.createInstance(TipoCaixa.REFRIGERADA, 85));
        p.addCaixa(cf.createInstance(TipoCaixa.REFRIGERADA, 18));
        p.addCaixa(cf.createInstance(TipoCaixa.PERECIVEL, 35, LocalDate.of(2016, 12, 12)));
        pedidos.add(p);

        p = new Pedido(LocalDate.of(2016, 12, 10), locais.getLocal(2));
        p.addCaixa(cf.createInstance(TipoCaixa.NORMAL, 360));
        p.addCaixa(cf.createInstance(TipoCaixa.NORMAL, 721));
        p.addCaixa(cf.createInstance(TipoCaixa.NORMAL, 190));
        p.addCaixa(cf.createInstance(TipoCaixa.PERECIVEL, 25, LocalDate.of(2016, 12, 11)));
        pedidos.add(p);

        p = new Pedido(LocalDate.of(2016, 12, 11), locais.getLocal(4));
        p.addCaixa(cf.createInstance(TipoCaixa.NORMAL, 500));
        p.addCaixa(cf.createInstance(TipoCaixa.NORMAL, 300));
        p.addCaixa(cf.createInstance(TipoCaixa.REFRIGERADA, 12));
        p.addCaixa(cf.createInstance(TipoCaixa.REFRIGERADA, 57));
        pedidos.add(p);
    }

    public static Pedidos getInstance() {
        return (lstPed);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Pedido getCorrente() {
        return (pedidos.get(corrente));
    }

    public Pedido getAnterior() {
        if (corrente > 0) {
            corrente--;
            return (pedidos.get(corrente));
        } else {
            return (null);
        }
    }

    @Override
    public String toString() {
        return "" + pedidos;
    }

    public Pedido getProximo() {
        if (corrente < pedidos.size() - 1) {
            corrente++;
            return (pedidos.get(corrente));
        } else {
            return (null);
        }
    }
}
