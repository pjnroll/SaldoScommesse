package pierluigilaviano.altervista.org.saldoscommesse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "SaldoScommesse";

    static final String TAGPaid = "PAID";
    static final String TAGWinning = "WINNING";

    boolean isPaidControllerVisible = false;
    boolean isWinningControllerVisible = false;

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

    int balCentinaia;
    int balDecine;
    int balUnita;
    int balDecimi;
    int balCentesimi;

    int payCentinaia;
    int payDecine;
    int payUnita;
    int payDecimi;
    int payCentesimi;

    int toPayCentinaia;
    int toPayDecine;
    int toPayUnita;
    int toPayDecimi;
    int toPayCentesimi;

    int winCentinaia;
    int winDecine;
    int winUnita;
    int winDecimi;
    int winCentesimi;

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

        balCentinaia = 0;
        balDecine = 0;
        balUnita = 0;
        balDecimi = 0;
        balCentesimi = 0;

        /**
         * Referenzio le label contenenti i valori della sezione Paga
         */
        mLblPayCentinaia = (TextView) findViewById(R.id.lblPayCentinaia);
        mLblPayDecine = (TextView) findViewById(R.id.lblPayDecine);
        mLblPayUnita = (TextView) findViewById(R.id.lblPayUnita);
        mLblPayDecimi = (TextView) findViewById(R.id.lblPayDecimi);
        mLblPayCentesimi = (TextView) findViewById(R.id.lblPayCentesimi);

        mLblWinCentinaia = (TextView) findViewById(R.id.lblWinCentinaia);
        mLblWinDecine = (TextView) findViewById(R.id.lblWinDecine);
        mLblWinUnita = (TextView) findViewById(R.id.lblWinUnita);
        mLblWinDecimi = (TextView) findViewById(R.id.lblWinDecimi);
        mLblWinCentesimi = (TextView) findViewById(R.id.lblWinCentesimi);

        /**
         * Assegno i valori delle label agli attributi interi
         */
        toPayCentinaia = Integer.parseInt(mLblPayCentinaia.getText().toString());
        toPayDecine = Integer.parseInt(mLblPayDecine.getText().toString());
        toPayUnita = Integer.parseInt(mLblPayUnita.getText().toString());
        toPayDecimi = Integer.parseInt(mLblPayDecimi.getText().toString());
        toPayCentesimi = Integer.parseInt(mLblPayCentesimi.getText().toString());

        toWinCentinaia = Integer.parseInt(mLblWinCentinaia.getText().toString());
        toWinDecine = Integer.parseInt(mLblWinDecine.getText().toString());
        toWinUnita = Integer.parseInt(mLblWinUnita.getText().toString());
        toWinDecimi = Integer.parseInt(mLblWinDecimi.getText().toString());
        toWinCentesimi = Integer.parseInt(mLblWinCentesimi.getText().toString());

        /**
         * Attiva/Disattiva i pulsanti "controller" per la sezione Paga
         */
        mBtnAddPaid = (Button) findViewById(R.id.btnAddPaid);
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
                    //addWinning();
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
                if (toPayCentinaia > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toPayCentinaia++;
                    mLblPayCentinaia.setText(String.valueOf(toPayCentinaia));
                }
            }
        });

        mBtnPayRemCentinaia = (Button) findViewById(R.id.btnPayRemCentinaia);
        mBtnPayRemCentinaia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPayCentinaia < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toPayCentinaia--;
                    mLblPayCentinaia.setText(String.valueOf(toPayCentinaia));
                }
            }
        });

        mBtnPayAddDecine = (Button) findViewById(R.id.btnPayAddDecine);
        mBtnPayAddDecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPayDecine > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toPayDecine++;
                    mLblPayDecine.setText(String.valueOf(toPayDecine));
                }
            }
        });

        mBtnPayRemDecine = (Button) findViewById(R.id.btnPayRemDecine);
        mBtnPayRemDecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPayDecine < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toPayDecine--;
                    mLblPayDecine.setText(String.valueOf(toPayDecine));
                }
            }
        });

        mBtnPayAddUnita = (Button) findViewById(R.id.btnPayAddUnita);
        mBtnPayAddUnita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPayUnita > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toPayUnita++;
                    mLblPayUnita.setText(String.valueOf(toPayUnita));
                }
            }
        });

        mBtnPayRemUnita = (Button) findViewById(R.id.btnPayRemUnita);
        mBtnPayRemUnita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPayUnita < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toPayUnita--;
                    mLblPayUnita.setText(String.valueOf(toPayUnita));
                }
            }
        });

        mBtnPayAddDecimi = (Button) findViewById(R.id.btnPayAddDecimi);
        mBtnPayAddDecimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPayDecimi > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toPayDecimi++;
                    mLblPayDecimi.setText(String.valueOf(toPayDecimi));
                }
            }
        });

        mBtnPayRemDecimi = (Button) findViewById(R.id.btnPayRemDecimi);
        mBtnPayRemDecimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPayDecimi < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toPayDecimi--;
                    mLblPayDecimi.setText(String.valueOf(toPayDecimi));
                }
            }
        });

        mBtnPayAddCentesimi = (Button) findViewById(R.id.btnPayAddCentesimi);
        mBtnPayAddCentesimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPayCentesimi > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toPayCentesimi++;
                    mLblPayCentesimi.setText(String.valueOf(toPayCentesimi));
                }
            }
        });

        mBtnPayRemCentesimi = (Button) findViewById(R.id.btnPayRemCentesimi);
        mBtnPayRemCentesimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toPayCentesimi < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toPayCentesimi--;
                    mLblPayCentesimi.setText(String.valueOf(toPayCentesimi));
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
                if (toWinCentinaia > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toWinCentinaia++;
                    mLblWinCentinaia.setText(String.valueOf(toWinCentinaia));
                }
            }
        });

        mBtnWinRemCentinaia = (Button) findViewById(R.id.btnWinRemCentinaia);
        mBtnWinRemCentinaia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWinCentinaia < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toWinCentinaia--;
                    mLblWinCentinaia.setText(String.valueOf(toWinCentinaia));
                }
            }
        });

        mBtnWinAddDecine = (Button) findViewById(R.id.btnWinAddDecine);
        mBtnWinAddDecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWinDecine > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toWinDecine++;
                    mLblWinDecine.setText(String.valueOf(toWinDecine));
                }
            }
        });

        mBtnWinRemDecine = (Button) findViewById(R.id.btnWinRemDecine);
        mBtnWinRemDecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWinDecine < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toWinDecine--;
                    mLblWinDecine.setText(String.valueOf(toWinDecine));
                }
            }
        });

        mBtnWinAddUnita = (Button) findViewById(R.id.btnWinAddUnita);
        mBtnWinAddUnita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWinUnita > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toWinUnita++;
                    mLblWinUnita.setText(String.valueOf(toWinUnita));
                }
            }
        });

        mBtnWinRemUnita = (Button) findViewById(R.id.btnWinRemUnita);
        mBtnWinRemUnita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWinUnita < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toWinUnita--;
                    mLblWinUnita.setText(String.valueOf(toWinUnita));
                }
            }
        });

        mBtnWinAddDecimi = (Button) findViewById(R.id.btnWinAddDecimi);
        mBtnWinAddDecimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWinDecimi > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toWinDecimi++;
                    mLblWinDecimi.setText(String.valueOf(toWinDecimi));
                }
            }
        });

        mBtnWinRemDecimi = (Button) findViewById(R.id.btnWinRemDecimi);
        mBtnWinRemDecimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWinDecimi < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toWinDecimi--;
                    mLblWinDecimi.setText(String.valueOf(toWinDecimi));
                }
            }
        });

        mBtnWinAddCentesimi = (Button) findViewById(R.id.btnWinAddCentesimi);
        mBtnWinAddCentesimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWinCentesimi > 8) {
                    Log.e(TAG, "Impossibile andare oltre il 9");
                } else {
                    toWinCentesimi++;
                    mLblWinCentesimi.setText(String.valueOf(toWinCentesimi));
                }
            }
        });

        mBtnWinRemCentesimi = (Button) findViewById(R.id.btnWinRemCentesimi);
        mBtnWinRemCentesimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toWinCentesimi < 1) {
                    Log.e(TAG, "Impossibile andare oltre lo 0");
                } else {
                    toWinCentesimi--;
                    mLblWinCentesimi.setText(String.valueOf(toWinCentesimi));
                }
            }
        });

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

    void addPay() {
        double pay = (toPayCentinaia *100) +
                (toPayDecine *10) +
                (toPayUnita) +
                (((double)((toPayDecimi) + (toPayCentesimi)))/100);
        Log.i(TAG, "Da pagare->" + pay);
        int balBeforeCentinaia = Integer.valueOf(((TextView)findViewById(R.id.lblBalCentinaia)).getText().toString());
        int balBeforeDecine = Integer.valueOf(((TextView)findViewById(R.id.lblBalDecine)).getText().toString());
        int balBeforeUnita = Integer.valueOf(((TextView)findViewById(R.id.lblBalUnita)).getText().toString());
        int balBeforeDecimi = Integer.valueOf(((TextView)findViewById(R.id.lblBalDecimi)).getText().toString());
        int balBeforeCentesimi = Integer.valueOf(((TextView)findViewById(R.id.lblBalCentesimi)).getText().toString());
        double balBefore = (balBeforeCentinaia *100) +
                (balBeforeDecine * 10) +
                (balBeforeUnita) +
                ((double) balBeforeDecimi/10) +
                ((double) balBeforeCentesimi/100);
        Log.i(TAG, "BalBefore->" + balBefore);

        double actualBalance = balBefore - pay;
        Log.i(TAG, "balAfter->" + actualBalance);

        double absActualBalance = Math.abs(actualBalance);

        int balAfterCentinaia = 0;
        int balAfterDecine = 0;
        int balAfterUnita = 0;
        int balAfterDecimi = 0;
        int balAfterCentesimi = 0;

        if (absActualBalance > 99.99) {
            balAfterCentinaia = ((int)absActualBalance)/100;
            balAfterDecine = ((int)absActualBalance)/10 -
                    (balAfterCentinaia *10);
            balAfterUnita = (int)absActualBalance -
                    ((balAfterCentinaia *100) + (balAfterDecine *10));
            balAfterDecimi = ((int)absActualBalance)*10 - ((balAfterCentinaia *1000) + (balAfterDecine *100) + (balAfterUnita *10));
            balAfterCentesimi = ((int)absActualBalance)*100 - ((balAfterCentinaia*10000) + (balAfterDecine*1000) +
                    (balAfterUnita *100) + (balAfterDecimi *10));
        } else if (absActualBalance < 100 && absActualBalance > 9.99) {
            balAfterDecine = ((int)absActualBalance)/10;
            balAfterUnita = (int)absActualBalance - (balAfterDecine *10);
            balAfterDecimi = ((int)absActualBalance) *10 - ((balAfterDecine * 100) + (balAfterUnita *10));
            balAfterCentesimi = ((int)absActualBalance) *100 - ((balAfterDecine *1000) + (balAfterUnita *100) + (balAfterDecimi *10));
        } else if (absActualBalance < 10.0 && absActualBalance > 0.99) {
            balAfterUnita = (int)absActualBalance;
            Log.i(TAG, "balAfterUnita->" + balAfterUnita);
            balAfterDecimi = (int)(absActualBalance *10) - (balAfterUnita *10);
            Log.i(TAG, "balAfterDecimi->" + balAfterDecimi);
            balAfterCentesimi = ((int)absActualBalance) *100 - ((balAfterUnita *100) + (balAfterDecimi *10));
            Log.i(TAG, "balAfterCentesimi->" + balAfterCentesimi);
        } else if (absActualBalance < 1.0 && absActualBalance > 0.09) {
            balAfterDecimi = (int)absActualBalance *10;
            balAfterCentesimi = ((int)absActualBalance) *100 - (balAfterDecimi *10);
        } else if (absActualBalance < 0.1) {
            balAfterCentesimi = (int)absActualBalance *100;
        }

        Log.i(TAG, "Complete->" + balAfterCentinaia + balAfterDecine + balAfterUnita + "." + balAfterDecimi + balAfterCentesimi);

        ((TextView)findViewById(R.id.lblBalCentinaia)).setText(String.valueOf(balAfterCentinaia));
        ((TextView)findViewById(R.id.lblBalDecine)).setText(String.valueOf(balAfterDecine));
        ((TextView)findViewById(R.id.lblBalUnita)).setText(String.valueOf(balAfterUnita));
        ((TextView)findViewById(R.id.lblBalDecimi)).setText(String.valueOf(balAfterDecimi));
        ((TextView)findViewById(R.id.lblBalCentesimi)).setText(String.valueOf(balAfterCentesimi));

        /*int balAfterDecine = (int)actualBalance / 10;
        int balAfterUnita = (int)actualBalance - (balAfterDecine*10);
        int balAfterDecimi = (int)((actualBalance - ((balAfterDecine*10) + balAfterUnita))*10);
        //int balAfterCentesimi = (int)((actualBalance - ((balAfterDecine*10) + balAfterUnita))*100);*/


        //mLblBalDecine.setText();
    }
}
