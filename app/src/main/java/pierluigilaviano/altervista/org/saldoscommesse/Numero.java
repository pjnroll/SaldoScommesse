package pierluigilaviano.altervista.org.saldoscommesse;

import android.util.Log;

/**
 * Created by pier on 27/09/17.
 */

public class Numero {
    private final static int MAX_N = 5;
    public static final String TAG = "SaldoScommesse";

    private int centinaia;
    private int decine;
    private int unita;
    private int decimi;
    private int centesimi;

    public Numero() {
        this(0, 0, 0, 0, 0);
    }

    public Numero(int centinaia, int decine, int unita, int decimi, int centesimi) {
        this.centinaia = centinaia;
        this.decine = decine;
        this.unita = unita;
        this.decimi = decimi;
        this.centesimi = centesimi;
    }

    private Numero(int decine, int unita, int decimi, int centesimi) {
        this(0, decine, unita, decimi, centesimi);
    }

    private Numero(int unita, int decimi, int centesimi) {
        this(0, 0, unita, decimi, centesimi);
    }

    private Numero(int decimi, int centesimi) {
        this(0, 0, 0, decimi, centesimi);
    }

    private Numero(int centesimi) {
        this(0, 0, 0, 0, centesimi);
    }

    public void setCentinaia(int centinaia) {
        this.centinaia = centinaia;
    }

    public void setDecine(int decine) {
        this.decine = decine;
    }

    public void setUnita(int unita) {
        this.unita = unita;
    }

    public void setDecimi(int decimi) {
        this.decimi = decimi;
    }

    public void setCentesimi(int centesimi) {
        this.centesimi = centesimi;
    }

    public int getCentinaia() {
        return centinaia;
    }

    public int getDecine() {
        return decine;
    }

    public int getUnita() {
        return unita;
    }

    public int getDecimi() {
        return decimi;
    }

    public int getCentesimi() {
        return centesimi;
    }

    public void addCentinaia() {
        centinaia++;
    }

    public void addDecine() {
        decine++;
    }

    public void addUnita() {
        unita++;
    }

    public void addDecimi() {
        decimi++;
    }

    public void addCentesimi() {
        centesimi++;
    }

    public void remCentinaia() {
        centinaia--;
    }

    public void remDecine() {
        decine--;
    }

    public void remUnita() {
        unita--;
    }

    public void remDecimi() {
        decimi--;
    }

    public void remCentesimi() {
        centesimi--;
    }

    public static double getDouble(Numero n) {
        int centinaia = Math.abs(n.getCentinaia())*10000;
        int decine = Math.abs(n.getDecine())*1000;
        int unita = Math.abs(n.getUnita())*100;
        double decimi = ((double)Math.abs(n.getDecimi()))*10;
        double centesimi = ((double)Math.abs(n.getCentesimi()));

        double d = (centinaia + decine + unita + decimi + centesimi)/100;

        /**
         * Questa condizione sarà vera solo con balance
         */
        if (Numero.isNegative(n)) {
            d = -d;
        }

        return d;
    }

    public Numero sumTo(Numero nO) {
        double dT = Numero.getDouble(this);
        double dO = Numero.getDouble(nO);

        double sum = (dT*100 + dO*100)/100;

        return Numero.getNumero(sum);
    }

    public Numero diffTo(Numero nO) {
        double dT = Numero.getDouble(this);
        double dO = Numero.getDouble(nO);
        double diff = (dT*100 - dO*100)/100;

        return Numero.getNumero(diff);
    }

    public static boolean isNegative(Numero n) {
        boolean f = false;

        int[] ns = new int[MAX_N];
        ns[0] = n.getCentinaia();
        ns[1] = n.getDecine();
        ns[2] = n.getUnita();
        ns[3] = n.getDecimi();
        ns[4] = n.getCentesimi();

        for (int i = 0; i < MAX_N && f == false && ns[i] <= 0; i++) {
            if (ns[i] < 0) {
                f = true;
            }
        }

        return f;
    }

    public static Numero getNumero(double d) {
        int centinaia;
        int decine;
        int unita;
        int decimi;
        int centesimi;

        Numero n = null;

        double dAbs = Math.abs(d);

        if (dAbs > 99.99) {
            centinaia = (int)(dAbs/100);
            decine = ((int)(dAbs/10)) - (centinaia * 10);
            unita = ((int)dAbs) - ((centinaia * 100) + (decine * 10));
            decimi = (int) ((dAbs - ((centinaia * 100) + (decine * 10) + unita))*10);
            centesimi = (int)((dAbs*100) - (((double)centinaia)*100 + ((double)decine)*10 + ((double)unita) + ((double)decimi)/10)*100);
            if (d < 0) {
                centinaia = -centinaia;
            }
            n = new Numero(centinaia, decine, unita, decimi, centesimi);
        } else if (dAbs < 100 && dAbs > 9.99) {
            decine = (int)(dAbs/10);
            unita = ((int)(dAbs)) - (decine * 10);
            decimi = (int)((dAbs - ((decine*10) + unita))*10);
            centesimi = (int)((dAbs*100) - ((decine*10) + unita + ((double)decimi)/10)*100);
            if (d < 0) {
                decine = -decine;
            }
            n = new Numero(decine, unita, decimi, centesimi);
        } else if (dAbs < 10.0 && dAbs > 0.99) {
            unita = (int)(dAbs);
            decimi = (int)((dAbs - unita) * 10);
            centesimi = (int)((dAbs*100 - ((unita*100) + (decimi*10))));
            if (d < 0) {
                unita = -unita;
            }
            n = new Numero(unita, decimi, centesimi);
        } else if (dAbs < 1.0 && dAbs > 0.09) {
            decimi = (int) (dAbs * 10);
            centesimi = (int) ((dAbs*100) - decimi*10);
            if (d < 0) {
                decimi = -decimi;
            }
            n = new Numero(decimi, centesimi);
        } else if (dAbs < 0.1) {
            centesimi = (int)(dAbs*100);
            if (d < 0) {
                centesimi = -centesimi;
            }
            n = new Numero(centesimi);
        }

        return n;
    }

    public static void empty(Numero n) {
        n.setCentinaia(0);
        n.setDecine(0);
        n.setUnita(0);
        n.setDecimi(0);
        n.setCentesimi(0);
    }

    public String toString() {
        return "Numero:" + getCentinaia() + getDecine() + getUnita() + "." + getDecimi() + getCentesimi();
    }
}
