package javatf;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class CaixaFactory {

    private static CaixaFactory cf = new CaixaFactory();
    private static int cxId = 0;

    private CaixaFactory() {
    }

    public static CaixaFactory getInstance() {
        return (cf);
    }

    public Caixa createInstance(TipoCaixa tx, int peso, LocalDate dtVal) {
        cxId++;
        String id = "Cx" + cxId;
        switch (tx) {
            case NORMAL:
                return (new CxNormal(id, peso));
            case REFRIGERADA:
                return (new CxRefrigerada(id, peso));
            case PERECIVEL:
                return (new CxPerecivel(id, peso, dtVal));
        }
        return null;
    }

    public Caixa createInstance(TipoCaixa tx, int peso) {
        return (createInstance(tx, peso, null));
    }
}
