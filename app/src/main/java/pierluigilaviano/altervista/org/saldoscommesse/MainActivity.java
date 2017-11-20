package pierluigilaviano.altervista.org.saldoscommesse;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "SaldoScommesse";

    static final String TAGPaid = "PAID";
    static final String TAGWinning = "WINNING";
    public static final String ERR_ABOVE = "Impossibile andare oltre il 9";
    public static final String ERR_BELOW = "Impossibile andare oltre lo 0";
    public static final String ZERO = "0";

    SharedPreferences sp;
    final static String SP_BAL = "SP_BAL";
    final static String SP_PAY = "SP_PAY";
    final static String SP_WIN = "SP_WIN";

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

<<<<<<< HEAD
    //Numero balance;
    int balCentinaia;
    int balDecine;
    int balUnita;
    int balDecimi;
    int balCentesimi;

    //Numero pay;
    int payCentinaia;
    int payDecine;
    int payUnita;
    int payDecimi;
    int payCentesimi;

    //Numero toPay;
=======
    private Numero balance;

    private Numero pay;

    private Numero toPay;
>>>>>>> 9aa15a3dddf9173672b311a660e2aa32937cb87f
    int toPayCentinaia;
    int toPayDecine;
    int toPayUnita;
    int toPayDecimi;
    int toPayCentesimi;

<<<<<<< HEAD
    //Numero win;
    int winCentinaia;
    int winDecine;
    int winUnita;
    int winDecimi;
    int winCentesimi;

    //Numero toWin;
=======
    private Numero win;

    private Numero toWin;
>>>>>>> 9aa15a3dddf9173672b311a660e2aa32937cb87f
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

<<<<<<< HEAD
        //balance = new Numero(0, 0, 0, 0, 0);
        balCentinaia = 0;
        balDecine = 0;
        balUnita = 0;
        balDecimi = 0;
        balCentesimi = 0;
=======
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
>>>>>>> 9aa15a3dddf9173672b311a660e2aa32937cb87f

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

<<<<<<< HEAD
        /**
         * Assegno i valori delle label agli attributi interi
         */
        //TODO Da integrare quando implemento la persistenza

        /*toPayCentinaia = Integer.parseInt(mLblPayCentinaia.getText().toString());
        toPayDecine = Integer.parseInt(mLblPayDecine.getText().toString());
        toPayUnita = Integer.parseInt(mLblPayUnita.getText().toString());
        toPayDecimi = Integer.parseInt(mLblPayDecimi.getText().toString());
        toPayCentesimi = Integer.parseInt(mLblPayCentesimi.getText().toString());

        toWinCentinaia = Integer.parseInt(mLblWinCentinaia.getText().toString());
        toWinDecine = Integer.parseInt(mLblWinDecine.getText().toString());
        toWinUnita = Integer.parseInt(mLblWinUnita.getText().toString());
        toWinDecimi = Integer.parseInt(mLblWinDecimi.getText().toString());
        toWinCentesimi = Integer.parseInt(mLblWinCentesimi.getText().toString());*/
=======
        initialize();
>>>>>>> 9aa15a3dddf9173672b311a660e2aa32937cb87f

        /**
         * Istanzio i pulsanti per attivare/disattivare i Pagamenti e le riscossioni
         */
        mBtnAddPaid = (Button) findViewById(R.id.btnAddPaid);
<<<<<<< HEAD
        mBtnAddPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPaidControllerVisible) {
                    addPay();

                    mLblPayCentinaia.setText(String.valueOf(balCentinaia));
                    mLblPayDecine.setText(String.valueOf(balDecine));
                    mLblPayUnita.setText(String.valueOf(balUnita));
                    mLblPayDecimi.setText(String.valueOf(balDecimi));
                    mLblPayCentesimi.setText(String.valueOf(balCentesimi));
                } else {
                    mLblPayCentinaia.setText("0");
                    mLblPayDecine.setText("0");
                    mLblPayUnita.setText("0");
                    mLblPayDecimi.setText("0");
                    mLblPayCentesimi.setText("0");

                    balCentinaia = 0;
                    balDecine = 0;
                    balUnita = 0;
                    balDecimi = 0;
                    balCentesimi = 0;
                }

                isPaidControllerVisible = !isPaidControllerVisible;
                setControllerVisible(TAGPaid, isPaidControllerVisible);
            }
        });

        /**
         * Attiva/Disattiva i pulsanti "controller" per la sezione Riscuoti
         */
=======
>>>>>>> 9aa15a3dddf9173672b311a660e2aa32937cb87f
        mBtnAddWinning = (Button) findViewById(R.id.btnAddWinning);


        /**
         * Istanzio i pulsanti per gestire i Pagamenti
         */
        mBtnPayAddCentinaia = (Button) findViewById(R.id.btnPayAddCentinaia);
        mBtnPayRemCentinaia = (Button) findViewById(R.id.btnPayRemCentinaia);
        mBtnPayAddDecine = (Button) findViewById(R.id.btnPayAddDecine);
        mBtnPayRemDecine = (Button) findViewById(R.id.btnPayRemDecine);
        mBtnPayAddUnita = (Button) findViewById(R.id.btnPayAddUnita);
        mBtnPayRemUnita = (Button) findViewById(R.id.btnPayRemUnita);
        mBtnPayAddDecimi = (Button) findViewById(R.id.btnPayAddDecimi);
        mBtnPayRemDecimi = (Button) findViewById(R.id.btnPayRemDecimi);
        mBtnPayAddCentesimi = (Button) findViewById(R.id.btnPayAddCentesimi);
        mBtnPayRemCentesimi = (Button) findViewById(R.id.btnPayRemCentesimi);

        /**
         * Istanzio i pulsanti per gestire le Riscossioni
         */
        mBtnWinAddCentinaia = (Button) findViewById(R.id.btnWinAddCentinaia);
        mBtnWinRemCentinaia = (Button) findViewById(R.id.btnWinRemCentinaia);
        mBtnWinAddDecine = (Button) findViewById(R.id.btnWinAddDecine);
        mBtnWinRemDecine = (Button) findViewById(R.id.btnWinRemDecine);
        mBtnWinAddUnita = (Button) findViewById(R.id.btnWinAddUnita);
        mBtnWinRemUnita = (Button) findViewById(R.id.btnWinRemUnita);
        mBtnWinAddDecimi = (Button) findViewById(R.id.btnWinAddDecimi);
        mBtnWinRemDecimi = (Button) findViewById(R.id.btnWinRemDecimi);
        mBtnWinAddCentesimi = (Button) findViewById(R.id.btnWinAddCentesimi);
        mBtnWinRemCentesimi = (Button) findViewById(R.id.btnWinRemCentesimi);

        View.OnClickListener manager = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseArray<Runnable> actions = new SparseArray<>();

                /**
                 * Attiva/Disattiva i pulsanti "controller" per la sezione Paga
                 */
                actions.put(R.id.btnAddPaid, new Runnable() {
                    @Override
                    public void run() {
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

                            mLblPayCentinaia.setText(ZERO);
                            mLblPayDecine.setText(ZERO);
                            mLblPayUnita.setText(ZERO);
                            mLblPayDecimi.setText(ZERO);
                            mLblPayCentesimi.setText(ZERO);
                        }

                        isPaidControllerVisible = !isPaidControllerVisible;
                        setControllerVisible(TAGPaid, isPaidControllerVisible);
                    }
                });

                /**
                 * Attiva/Disattiva i pulsanti "controller" per la sezione Riscuoti
                 */
                actions.put(R.id.btnAddWinning, new Runnable() {
                    @Override
                    public void run() {
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

                            mLblWinCentinaia.setText(ZERO);
                            mLblWinDecine.setText(ZERO);
                            mLblWinUnita.setText(ZERO);
                            mLblWinDecimi.setText(ZERO);
                            mLblWinCentesimi.setText(ZERO);
                        }
                        isWinningControllerVisible = !isWinningControllerVisible;
                        setControllerVisible(TAGWinning, isWinningControllerVisible);
                    }
                });

                /**
                 * Gestione dei tasti + e - dei controller Paga
                 */
                actions.put(R.id.btnPayAddCentinaia, new Runnable() {
                    @Override
                    public void run() {
                        if (toPay.getCentinaia() > 8) {
                            Log.e(TAG, ERR_ABOVE);
                        } else {
                            toPay.addCentinaia();
                            mLblPayCentinaia.setText(String.valueOf(toPay.getCentinaia()));
                        }
                    }
                });


                actions.put(R.id.btnPayRemCentinaia, new Runnable() {
                    @Override
                    public void run() {
                        if (toPay.getCentinaia() < 1) {
                            Log.e(TAG, ERR_BELOW);
                        } else {
                            toPay.remCentinaia();
                            mLblPayCentinaia.setText(String.valueOf(toPay.getCentinaia()));
                        }
                    }
                });

                actions.put(R.id.btnPayAddDecine, new Runnable() {
                    @Override
                    public void run() {
                        if (toPay.getDecine() > 8) {
                            Log.e(TAG, ERR_ABOVE);
                        } else {
                            toPay.addDecine();
                            mLblPayDecine.setText(String.valueOf(toPay.getDecine()));
                        }
                    }
                });

                actions.put(R.id.btnPayRemDecine, new Runnable() {
                    @Override
                    public void run() {
                        if (toPay.getDecine() < 1) {
                            Log.e(TAG, ERR_BELOW);
                        } else {
                            toPay.remDecine();
                            mLblPayDecine.setText(String.valueOf(toPay.getDecine()));
                        }
                    }
                });

                actions.put(R.id.btnPayAddUnita, new Runnable() {
                    @Override
                    public void run() {
                        if (toPay.getUnita() > 8) {
                            Log.e(TAG, ERR_ABOVE);
                        } else {
                            toPay.addUnita();
                            mLblPayUnita.setText(String.valueOf(toPay.getUnita()));
                        }
                    }
                });
                actions.put(R.id.btnPayRemUnita, new Runnable() {
                    @Override
                    public void run() {
                        if (toPay.getUnita() < 1) {
                            Log.e(TAG, ERR_BELOW);
                        } else {
                            toPay.remUnita();
                            mLblPayUnita.setText(String.valueOf(toPay.getUnita()));
                        }
                    }
                });
                actions.put(R.id.btnPayAddDecimi, new Runnable() {
                    @Override
                    public void run() {
                        if (toPay.getDecimi() > 8) {
                            Log.e(TAG, ERR_ABOVE);
                        } else {
                            toPay.addDecimi();
                            mLblPayDecimi.setText(String.valueOf(toPay.getDecimi()));
                        }
                    }
                });

                actions.put(R.id.btnPayRemDecimi, new Runnable() {
                    @Override
                    public void run() {
                        if (toPay.getDecimi() < 1) {
                            Log.e(TAG, ERR_BELOW);
                        } else {
                            toPay.remDecimi();
                            mLblPayDecimi.setText(String.valueOf(toPay.getDecimi()));
                        }
                    }
                });

                actions.put(R.id.btnPayAddCentesimi, new Runnable() {
                    @Override
                    public void run() {
                        if (toPay.getCentesimi() > 8) {
                            Log.e(TAG, ERR_ABOVE);
                        } else {
                            toPay.addCentesimi();
                            mLblPayCentesimi.setText(String.valueOf(toPay.getCentesimi()));
                        }
                    }
                });

                actions.put(R.id.btnPayRemCentesimi, new Runnable() {
                    @Override
                    public void run() {
                        if (toPay.getCentesimi() < 1) {
                            Log.e(TAG, ERR_BELOW);
                        } else {
                            toPay.remCentesimi();
                            mLblPayCentesimi.setText(String.valueOf(toPay.getCentesimi()));
                        }
                    }
                });

                /**
                 * Gestione dei tasti + e - dei controller Riscuoti
                 */
                actions.put(R.id.btnWinAddCentinaia, new Runnable() {
                    @Override
                    public void run() {
                        if (toWin.getCentinaia() > 8) {
                            Log.e(TAG, ERR_ABOVE);
                        } else {
                            toWin.addCentinaia();
                            mLblWinCentinaia.setText(String.valueOf(toWin.getCentinaia()));
                        }
                    }
                });

                actions.put(R.id.btnWinRemCentinaia, new Runnable() {
                    @Override
                    public void run() {
                        if (toWin.getCentinaia() < 1) {
                            Log.e(TAG, ERR_BELOW);
                        } else {
                            toWin.remCentinaia();
                            mLblWinCentinaia.setText(String.valueOf(toWin.getCentinaia()));
                        }
                    }
                });

                actions.put(R.id.btnWinAddDecine, new Runnable() {
                    @Override
                    public void run() {
                        if (toWin.getDecine() > 8) {
                            Log.e(TAG, ERR_ABOVE);
                        } else {
                            toWin.addDecine();
                            mLblWinDecine.setText(String.valueOf(toWin.getDecine()));
                        }
                    }
                });


                actions.put(R.id.btnWinRemDecine, new Runnable() {
                    @Override
                    public void run() {
                        if (toWin.getDecine() < 1) {
                            Log.e(TAG, ERR_BELOW);
                        } else {
                            toWin.remDecine();
                            mLblWinDecine.setText(String.valueOf(toWin.getDecine()));
                        }
                    }
                });
                actions.put(R.id.btnWinAddUnita, new Runnable() {
                    @Override
                    public void run() {
                        if (toWin.getUnita() > 8) {
                            Log.e(TAG, ERR_ABOVE);
                        } else {
                            toWin.addUnita();
                            mLblWinUnita.setText(String.valueOf(toWin.getUnita()));
                        }
                    }
                });


                actions.put(R.id.btnWinRemUnita, new Runnable() {
                    @Override
                    public void run() {
                        if (toWin.getUnita() < 1) {
                            Log.e(TAG, ERR_BELOW);
                        } else {
                            toWin.remUnita();
                            mLblWinUnita.setText(String.valueOf(toWin.getUnita()));
                        }
                    }
                });


                actions.put(R.id.btnWinAddDecimi, new Runnable() {
                    @Override
                    public void run() {
                        if (toWin.getDecimi() > 8) {
                            Log.e(TAG, ERR_ABOVE);
                        } else {
                            toWin.addDecimi();
                            mLblWinDecimi.setText(String.valueOf(toWin.getDecimi()));
                        }
                    }
                });


                actions.put(R.id.btnWinRemDecimi, new Runnable() {
                    @Override
                    public void run() {
                        if (toWin.getDecimi() < 1) {
                            Log.e(TAG, ERR_BELOW);
                        } else {
                            toWin.remDecimi();
                            mLblWinDecimi.setText(String.valueOf(toWin.getDecimi()));
                        }
                    }
                });


                actions.put(R.id.btnWinAddCentesimi, new Runnable() {
                    @Override
                    public void run() {
                        if (toWin.getCentesimi() > 8) {
                            Log.e(TAG, ERR_ABOVE);
                        } else {
                            toWin.addCentesimi();
                            mLblWinCentesimi.setText(String.valueOf(toWin.getCentesimi()));
                        }
                    }
                });


                actions.put(R.id.btnWinRemCentesimi, new Runnable() {
                    @Override
                    public void run() {
                        if (toWin.getCentesimi() < 1) {
                            Log.e(TAG, ERR_BELOW);
                        } else {
                            toWin.remCentesimi();
                            mLblWinCentesimi.setText(String.valueOf(toWin.getCentesimi()));
                        }
                    }
                });

                actions.get(v.getId()).run();
            }
        };
        setAllListener(manager);

    }

    private void setAllListener(View.OnClickListener manager) {

        mBtnAddPaid.setOnClickListener(manager);
        mBtnAddWinning.setOnClickListener(manager);

        mBtnPayAddCentinaia.setOnClickListener(manager);
        mBtnPayAddDecine.setOnClickListener(manager);
        mBtnPayAddUnita.setOnClickListener(manager);
        mBtnPayAddDecimi.setOnClickListener(manager);
        mBtnPayAddCentesimi.setOnClickListener(manager);

        mBtnPayRemCentinaia.setOnClickListener(manager);
        mBtnPayRemDecine.setOnClickListener(manager);
        mBtnPayRemUnita.setOnClickListener(manager);
        mBtnPayRemDecimi.setOnClickListener(manager);
        mBtnPayRemCentesimi.setOnClickListener(manager);

        mBtnWinAddCentinaia.setOnClickListener(manager);
        mBtnWinAddDecine.setOnClickListener(manager);
        mBtnWinAddUnita.setOnClickListener(manager);
        mBtnWinAddDecimi.setOnClickListener(manager);
        mBtnWinAddCentesimi.setOnClickListener(manager);

        mBtnWinRemCentinaia.setOnClickListener(manager);
        mBtnWinRemDecine.setOnClickListener(manager);
        mBtnWinRemUnita.setOnClickListener(manager);
        mBtnWinRemDecimi.setOnClickListener(manager);
        mBtnWinRemCentesimi.setOnClickListener(manager);
    }

    public void initialize() {
        int color = (Numero.isNegative(balance)) ? R.color.negBal : R.color.posBal;
        mLblBalSign.setTextColor(getResources().getColor(color));

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

    void addPay(Numero numberToPay) {
        balance = balance.diffTo(numberToPay);

        pay = pay.sumTo(numberToPay);
    }

    void addWin(Numero numberToWin) {
        balance = balance.sumTo(numberToWin);

        win = win.sumTo(numberToWin);
    }
}
