package pierluigilaviano.altervista.org.saldoscommesse;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "SaldoScommesse";

    static final String TAGPaid = "PAID";
    static final String TAGWinning = "WINNING";

    SharedPreferences sp;
    final static String SP_BAL = "SP_BAL";
    final static String SP_PAY = "SP_PAY";
    final static String SP_WIN = "SP_WIN";
    String FILENAME = "values";
    final static String SEP = "-";

    boolean isPaidControllerVisible = false;
    boolean isWinningControllerVisible = false;

    TextView mLblBalSign;
    TextView mLblBalCentinaia;
    TextView mLblBalDecine;
    TextView mLblBalUnita;
    TextView mLblBalDecimi;
    TextView mLblBalCentesimi;

    TextView mLblPayCentinaia;
    TextView mLblPayDecine;
    TextView mLblPayUnita;
    TextView mLblPayDecimi;
    TextView mLblPayCentesimi;

    TextView mLblWinCentinaia;
    TextView mLblWinDecine;
    TextView mLblWinUnita;
    TextView mLblWinDecimi;
    TextView mLblWinCentesimi;

    private Numero balance;

    private Numero pay;

    private Numero toPay;
    int toPayCentinaia;
    int toPayDecine;
    int toPayUnita;
    int toPayDecimi;
    int toPayCentesimi;

    private Numero win;

    private Numero toWin;
    int toWinCentinaia;
    int toWinDecine;
    int toWinUnita;
    int toWinDecimi;
    int toWinCentesimi;

    Button mBtnAddPaid;
    Button mBtnPayAddCentinaia;
    Button mBtnPayRemCentinaia;
    Button mBtnPayAddDecine;
    Button mBtnPayRemDecine;
    Button mBtnPayAddUnita;
    Button mBtnPayRemUnita;
    Button mBtnPayAddDecimi;
    Button mBtnPayRemDecimi;
    Button mBtnPayAddCentesimi;
    Button mBtnPayRemCentesimi;

    Button mBtnAddWinning;
    Button mBtnWinAddCentinaia;
    Button mBtnWinRemCentinaia;
    Button mBtnWinAddDecine;
    Button mBtnWinRemDecine;
    Button mBtnWinAddUnita;
    Button mBtnWinRemUnita;
    Button mBtnWinAddDecimi;
    Button mBtnWinRemDecimi;
    Button mBtnWinAddCentesimi;
    Button mBtnWinRemCentesimi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControllerVisible(TAGPaid, false);
        setControllerVisible(TAGWinning, false);

        balance = new Numero();
        pay = new Numero();
        win = new Numero();

        sp = getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        balance = Numero.getNumero((double) sp.getFloat(SP_BAL, 0));
        pay = Numero.getNumero((double) sp.getFloat(SP_PAY, 0));
        win = Numero.getNumero((double) sp.getFloat(SP_WIN, 0));

        /**
         * Referenzio le label contenenti i valori della sezione Saldo
         */
        mLblBalSign = (TextView) findViewById(R.id.lblBalSign);
        mLblBalCentinaia = (TextView) findViewById(R.id.lblBalCentinaia);
        mLblBalDecine = (TextView) findViewById(R.id.lblBalDecine);
        mLblBalUnita = (TextView) findViewById(R.id.lblBalUnita);
        mLblBalDecimi = (TextView) findViewById(R.id.lblBalDecimi);
        mLblBalCentesimi = (TextView) findViewById(R.id.lblBalCentesimi);

        /**
         * Referenzio le label contenenti i valori della sezione Paga
         */
        mLblPayCentinaia = (TextView) findViewById(R.id.lblPayCentinaia);
        mLblPayDecine = (TextView) findViewById(R.id.lblPayDecine);
        mLblPayUnita = (TextView) findViewById(R.id.lblPayUnita);
        mLblPayDecimi = (TextView) findViewById(R.id.lblPayDecimi);
        mLblPayCentesimi = (TextView) findViewById(R.id.lblPayCentesimi);

        /**
         * Referenzio le label contenenti i valori della sezione Riscuoti
         */
        mLblWinCentinaia = (TextView) findViewById(R.id.lblWinCentinaia);
        mLblWinDecine = (TextView) findViewById(R.id.lblWinDecine);
        mLblWinUnita = (TextView) findViewById(R.id.lblWinUnita);
        mLblWinDecimi = (TextView) findViewById(R.id.lblWinDecimi);
        mLblWinCentesimi = (TextView) findViewById(R.id.lblWinCentesimi);

        initialize();

        /**
         * Attiva/Disattiva i pulsanti "controller" per la sezione Paga
         */
        mBtnAddPaid = (Button) findViewById(R.id.btnAddPaid);
        mBtnAddPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPaidControllerVisible) {
                    toPayCentinaia = Integer.parseInt(mLblPayCentinaia.getText().toString());
                    toPayDecine = Integer.parseInt(mLblPayDecine.getText().toString());
                    toPayUnita = Integer.parseInt(mLblPayUnita.getText().toString());
                    toPayDecimi = Integer.parseInt(mLblPayDecimi.getText().toString());
                    toPayCentesimi = Integer.parseInt(mLblPayCentesimi.getText().toString());
                    toPay = new Numero(toPayCentinaia, toPayDecine, toPayUnita, toPayDecimi, toPayCentesimi);

                    addPay(toPay);
                    sp.edit().putFloat(SP_BAL, (float) Numero.getDouble(balance)).putFloat(SP_PAY, (float) Numero.getDouble(pay)).apply();

                    Numero.empty(toPay);

                    int sign = (Numero.isNegative(balance)) ? R.string.cap_minus : R.string.cap_plus;
                    int color = (Numero.isNegative(balance)) ? R.color.negBal : R.color.posBal;

                    mLblBalSign.setTextColor(getResources().getColor(color));
                    mLblBalSign.setText(sign);
                    mLblBalCentinaia.setText(String.valueOf(Math.abs(balance.getCentinaia())));
                    mLblBalDecine.setText(String.valueOf(Math.abs(balance.getDecine())));
                    mLblBalUnita.setText(String.valueOf(Math.abs(balance.getUnita())));
                    mLblBalDecimi.setText(String.valueOf(Math.abs(balance.getDecimi())));
                    mLblBalCentesimi.setText(String.valueOf(Math.abs(balance.getCentesimi())));


                    mLblPayCentinaia.setText(String.valueOf(pay.getCentinaia()));
                    mLblPayDecine.setText(String.valueOf(pay.getDecine()));
                    mLblPayUnita.setText(String.valueOf(pay.getUnita()));
                    mLblPayDecimi.setText(String.valueOf(pay.getDecimi()));
                    mLblPayCentesimi.setText(String.valueOf(pay.getCentesimi()));
                } else {
                    toPay = new Numero();

                    mLblPayCentinaia.setText("0");
                    mLblPayDecine.setText("0");
                    mLblPayUnita.setText("0");
                    mLblPayDecimi.setText("0");
                    mLblPayCentesimi.setText("0");
                }

                isPaidControllerVisible = !isPaidControllerVisible;
                setControllerVisible(TAGPaid, isPaidControllerVisible);
            }
        });

        /**
         * Attiva/Disattiva i pulsanti "controller" per la sezione Riscuoti
         */
        mBtnAddWinning = (Button) findViewById(R.id.btnAddWinning);
        mBtnAddWinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isWinningControllerVisible) {
                    toWinCentinaia = Integer.parseInt(mLblWinCentinaia.getText().toString());
                    toWinDecine = Integer.parseInt(mLblWinDecine.getText().toString());
                    toWinUnita = Integer.parseInt(mLblWinUnita.getText().toString());
                    toWinDecimi = Integer.parseInt(mLblWinDecimi.getText().toString());
                    toWinCentesimi = Integer.parseInt(mLblWinCentesimi.getText().toString());
                    toWin = new Numero(toWinCentinaia, toWinDecine, toWinUnita, toWinDecimi, toWinCentesimi);

                    addWin(toWin);

                    sp.edit().putFloat(SP_BAL, (float) Numero.getDouble(balance)).putFloat(SP_WIN, (float) Numero.getDouble(win)).apply();

                    Numero.empty(toWin);

                    int sign = (Numero.isNegative(balance)) ? R.string.cap_minus : R.string.cap_plus;
                    int color = (Numero.isNegative(balance)) ? R.color.negBal : R.color.posBal;

                    mLblBalSign.setTextColor(getResources().getColor(color));

                    mLblBalSign.setText(sign);
                    mLblBalCentinaia.setText(String.valueOf(Math.abs(balance.getCentinaia())));
                    mLblBalDecine.setText(String.valueOf(Math.abs(balance.getDecine())));
                    mLblBalUnita.setText(String.valueOf(Math.abs(balance.getUnita())));
                    mLblBalDecimi.setText(String.valueOf(Math.abs(balance.getDecimi())));
                    mLblBalCentesimi.setText(String.valueOf(Math.abs(balance.getCentesimi())));


                    mLblWinCentinaia.setText(String.valueOf(win.getCentinaia()));
                    mLblWinDecine.setText(String.valueOf(win.getDecine()));
                    mLblWinUnita.setText(String.valueOf(win.getUnita()));
                    mLblWinDecimi.setText(String.valueOf(win.getDecimi()));
                    mLblWinCentesimi.setText(String.valueOf(win.getCentesimi()));

                } else {
                    toWin = new Numero();

                    mLblWinCentinaia.setText("0");
                    mLblWinDecine.setText("0");
                    mLblWinUnita.setText("0");
                    mLblWinDecimi.setText("0");
                    mLblWinCentesimi.setText("0");
                }
                isWinningControllerVisible = !isWinningControllerVisible;
                setControllerVisible(TAGWinning, isWinningControllerVisible);
            }
        });

        /**
         * Gestione dei tasti + e - dei controller Paga
         */
        mBtnPayAddCentinaia = (Button) findViewById(R.id.btnPayAddCentinaia);
        mBtnPayAddCentinaia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPay.getCentinaia() > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toPay.addCentinaia();
                    mLblPayCentinaia.setText(String.valueOf(toPay.getCentinaia()));
                }
            }
        });

        mBtnPayRemCentinaia = (Button) findViewById(R.id.btnPayRemCentinaia);
        mBtnPayRemCentinaia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPay.getCentinaia() < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toPay.remCentinaia();
                    mLblPayCentinaia.setText(String.valueOf(toPay.getCentinaia()));
                }
            }
        });

        mBtnPayAddDecine = (Button) findViewById(R.id.btnPayAddDecine);
        mBtnPayAddDecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPay.getDecine() > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toPay.addDecine();
                    mLblPayDecine.setText(String.valueOf(toPay.getDecine()));
                }
            }
        });

        mBtnPayRemDecine = (Button) findViewById(R.id.btnPayRemDecine);
        mBtnPayRemDecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPay.getDecine() < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toPay.remDecine();
                    mLblPayDecine.setText(String.valueOf(toPay.getDecine()));
                }
            }
        });

        mBtnPayAddUnita = (Button) findViewById(R.id.btnPayAddUnita);
        mBtnPayAddUnita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPay.getUnita() > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toPay.addUnita();
                    mLblPayUnita.setText(String.valueOf(toPay.getUnita()));
                }
            }
        });

        mBtnPayRemUnita = (Button) findViewById(R.id.btnPayRemUnita);
        mBtnPayRemUnita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPay.getUnita() < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toPay.remUnita();
                    mLblPayUnita.setText(String.valueOf(toPay.getUnita()));
                }
            }
        });

        mBtnPayAddDecimi = (Button) findViewById(R.id.btnPayAddDecimi);
        mBtnPayAddDecimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPay.getDecimi() > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toPay.addDecimi();
                    mLblPayDecimi.setText(String.valueOf(toPay.getDecimi()));
                }
            }
        });

        mBtnPayRemDecimi = (Button) findViewById(R.id.btnPayRemDecimi);
        mBtnPayRemDecimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPay.getDecimi() < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toPay.remDecimi();
                    mLblPayDecimi.setText(String.valueOf(toPay.getDecimi()));
                }
            }
        });

        mBtnPayAddCentesimi = (Button) findViewById(R.id.btnPayAddCentesimi);
        mBtnPayAddCentesimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPay.getCentesimi() > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toPay.addCentesimi();
                    mLblPayCentesimi.setText(String.valueOf(toPay.getCentesimi()));
                }
            }
        });

        mBtnPayRemCentesimi = (Button) findViewById(R.id.btnPayRemCentesimi);
        mBtnPayRemCentesimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPay.getCentesimi() < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toPay.remCentesimi();
                    mLblPayCentesimi.setText(String.valueOf(toPay.getCentesimi()));
                }
            }
        });


        /**
         * Gestione dei tasti + e - dei controller Riscuoti
         */
        mBtnWinAddCentinaia = (Button) findViewById(R.id.btnWinAddCentinaia);
        mBtnWinAddCentinaia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWin.getCentinaia() > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toWin.addCentinaia();
                    mLblWinCentinaia.setText(String.valueOf(toWin.getCentinaia()));
                }
            }
        });

        mBtnWinRemCentinaia = (Button) findViewById(R.id.btnWinRemCentinaia);
        mBtnWinRemCentinaia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWin.getCentinaia() < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toWin.remCentinaia();
                    mLblWinCentinaia.setText(String.valueOf(toWin.getCentinaia()));
                }
            }
        });

        mBtnWinAddDecine = (Button) findViewById(R.id.btnWinAddDecine);
        mBtnWinAddDecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWin.getDecine() > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toWin.addDecine();
                    mLblWinDecine.setText(String.valueOf(toWin.getDecine()));
                }
            }
        });

        mBtnWinRemDecine = (Button) findViewById(R.id.btnWinRemDecine);
        mBtnWinRemDecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWin.getDecine() < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toWin.remDecine();
                    mLblWinDecine.setText(String.valueOf(toWin.getDecine()));
                }
            }
        });

        mBtnWinAddUnita = (Button) findViewById(R.id.btnWinAddUnita);
        mBtnWinAddUnita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWin.getUnita() > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toWin.addUnita();
                    mLblWinUnita.setText(String.valueOf(toWin.getUnita()));
                }
            }
        });

        mBtnWinRemUnita = (Button) findViewById(R.id.btnWinRemUnita);
        mBtnWinRemUnita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWin.getUnita() < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toWin.remUnita();
                    mLblWinUnita.setText(String.valueOf(toWin.getUnita()));
                }
            }
        });

        mBtnWinAddDecimi = (Button) findViewById(R.id.btnWinAddDecimi);
        mBtnWinAddDecimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWin.getDecimi() > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toWin.addDecimi();
                    mLblWinDecimi.setText(String.valueOf(toWin.getDecimi()));
                }
            }
        });

        mBtnWinRemDecimi = (Button) findViewById(R.id.btnWinRemDecimi);
        mBtnWinRemDecimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWin.getDecimi() < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toWin.remDecimi();
                    mLblWinDecimi.setText(String.valueOf(toWin.getDecimi()));
                }
            }
        });

        mBtnWinAddCentesimi = (Button) findViewById(R.id.btnWinAddCentesimi);
        mBtnWinAddCentesimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWin.getCentesimi() > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toWin.addCentesimi();
                    mLblWinCentesimi.setText(String.valueOf(toWin.getCentesimi()));
                }
            }
        });

        mBtnWinRemCentesimi = (Button) findViewById(R.id.btnWinRemCentesimi);
        mBtnWinRemCentesimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWin.getCentesimi() < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toWin.remCentesimi();
                    mLblWinCentesimi.setText(String.valueOf(toWin.getCentesimi()));
                }
            }
        });

    }

    public void initialize() {
        /**
         * Inizializzo le label del Saldo
         */
        mLblBalCentinaia.setText(String.valueOf(balance.getCentinaia()));
        mLblBalDecine.setText(String.valueOf(balance.getDecine()));
        mLblBalUnita.setText(String.valueOf(balance.getUnita()));
        mLblBalDecimi.setText(String.valueOf(balance.getDecimi()));
        mLblBalCentesimi.setText(String.valueOf(balance.getCentesimi()));

        /**
         * Inizializzo le label del Saldo
         */
        mLblPayCentinaia.setText(String.valueOf(pay.getCentinaia()));
        mLblPayDecine.setText(String.valueOf(pay.getDecine()));
        mLblPayUnita.setText(String.valueOf(pay.getUnita()));
        mLblPayDecimi.setText(String.valueOf(pay.getDecimi()));
        mLblPayCentesimi.setText(String.valueOf(pay.getCentesimi()));

        /**
         * Inizializzo le label del Saldo
         */
        mLblWinCentinaia.setText(String.valueOf(win.getCentinaia()));
        mLblWinDecine.setText(String.valueOf(win.getDecine()));
        mLblWinUnita.setText(String.valueOf(win.getUnita()));
        mLblWinDecimi.setText(String.valueOf(win.getDecimi()));
        mLblWinCentesimi.setText(String.valueOf(win.getCentesimi()));
    }

    void setControllerVisible(String tag, boolean b) {
        int v = (b) ? View.VISIBLE : View.INVISIBLE;

        switch (tag) {
            case TAGPaid: {
                findViewById(R.id.btnPayAddCentinaia).setVisibility(v);
                findViewById(R.id.btnPayAddDecine).setVisibility(v);
                findViewById(R.id.btnPayAddUnita).setVisibility(v);
                findViewById(R.id.btnPayAddDecimi).setVisibility(v);
                findViewById(R.id.btnPayAddCentesimi).setVisibility(v);

                findViewById(R.id.btnPayRemCentinaia).setVisibility(v);
                findViewById(R.id.btnPayRemDecine).setVisibility(v);
                findViewById(R.id.btnPayRemUnita).setVisibility(v);
                findViewById(R.id.btnPayRemDecimi).setVisibility(v);
                findViewById(R.id.btnPayRemCentesimi).setVisibility(v);

                break;
            }
            case TAGWinning: {
                findViewById(R.id.btnWinAddCentinaia).setVisibility(v);
                findViewById(R.id.btnWinAddDecine).setVisibility(v);
                findViewById(R.id.btnWinAddUnita).setVisibility(v);
                findViewById(R.id.btnWinAddDecimi).setVisibility(v);
                findViewById(R.id.btnWinAddCentesimi).setVisibility(v);

                findViewById(R.id.btnWinRemCentinaia).setVisibility(v);
                findViewById(R.id.btnWinRemDecine).setVisibility(v);
                findViewById(R.id.btnWinRemUnita).setVisibility(v);
                findViewById(R.id.btnWinRemDecimi).setVisibility(v);
                findViewById(R.id.btnWinRemCentesimi).setVisibility(v);

                break;
            }
        }
    }

    void addPay(Numero toPay) {
        Log.i(TAG, ("balance - toPay->" + Numero.getDouble(balance) + " - " + Numero.getDouble(toPay)));

        balance = balance.diffTo(toPay);
        Log.i("SaldoScommesse", "Saldo->" + Numero.getDouble(balance));

        pay = pay.sumTo(toPay);

    }

    void addWin(Numero toWin) {
        Log.i(TAG, ("balance + toWin->" + Numero.getDouble(balance) + " + " + Numero.getDouble(toWin)));

        balance = balance.sumTo(toWin);
        Log.i("SaldoScommesse", "Saldo->" + Numero.getDouble(balance));

        win = win.sumTo(toWin);
    }
}
