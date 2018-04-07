package com.dan.tictactoe;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class TwoPlayers extends AppCompatActivity {

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    int[][] boardStatus = new int[3][3];
    TextView tv1;
    Button button[][] = new Button[3][3];
    boolean SWITCH = false;


    LinearLayout llPlayer1, llPlayer2;
    TextView tvPlayer1, tvPlayer2;
    EditText etPlayer1, etPlayer2;
    Button btStart;
    TextView tvPlayer1Name, tvPlayer2Name, tvPlayer1Score, tvPlayer2Score, tvDraw, tvDrawScore, tvP1id, tvP2id;
    String player1, player2;
    int player1Score, player2Score, drawScore = 0;
    boolean CHANGE = false;
    Button tvInvert;
    String player1Icon = "X";
    String player2Icon = "0";
    Boolean INVERT = true;
    LinearLayout llSwap;
    TextView tvTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_two_players);


        button[0][0] = (Button) findViewById(R.id.button1);
        button[0][1] = (Button) findViewById(R.id.button2);
        button[0][2] = (Button) findViewById(R.id.button3);

        button[1][0] = (Button) findViewById(R.id.button4);
        button[1][1] = (Button) findViewById(R.id.button5);
        button[1][2] = (Button) findViewById(R.id.button6);

        button[2][0] = (Button) findViewById(R.id.button7);
        button[2][1] = (Button) findViewById(R.id.button8);
        button[2][2] = (Button) findViewById(R.id.button9);

        tv1 = (TextView) findViewById(R.id.tv1);
        llPlayer1 = findViewById(R.id.llPlayer1);
        llPlayer2 = findViewById(R.id.llPlayer2);
        tvPlayer1 = findViewById(R.id.tvPlayer1);
        tvPlayer2 = findViewById(R.id.tvPlayer2);
        etPlayer1 = findViewById(R.id.etPlayer1);
        etPlayer2 = findViewById(R.id.etPlayer2);
        tvPlayer1Name = findViewById(R.id.tvPlayer1Name);
        tvPlayer2Name = findViewById(R.id.tvPlayer2Name);
        tvPlayer1Score = findViewById(R.id.tvPlayer1Score);
        tvPlayer2Score = findViewById(R.id.tvPlayer2Score);
        tvDraw = findViewById(R.id.tvDraw);
        tvDrawScore = findViewById(R.id.tvDrawScore);
        tvInvert = findViewById(R.id.tvInvert);
        tvP1id = findViewById(R.id.tvP1id);
        tvP2id = findViewById(R.id.tvP2id);
        llSwap = findViewById(R.id.llSwap);
        tvTurn = findViewById(R.id.tvTurn);


        btStart = findViewById(R.id.btStart);

//        setUpVisibility();


        btStart.setText("Start");
        btStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (CHANGE == false) {


                    initializeBoardStatus();
//                    CHANGE = true;
                    onStopClick();
                    btStart.setText("Stop");


                } else if (CHANGE == true) {
                    btStart.setText("Start");

                    llPlayer1.setVisibility(View.VISIBLE);
                    llPlayer2.setVisibility(View.VISIBLE);
                    tvDrawScore.setVisibility(View.VISIBLE);
                    llSwap.setVisibility(View.VISIBLE);

                    tvPlayer1Score.setVisibility(View.GONE);
                    tvPlayer2Score.setVisibility(View.GONE);
                    tvPlayer1Name.setVisibility(View.GONE);
                    tvPlayer2Name.setVisibility(View.GONE);
                    tvDraw.setVisibility(View.GONE);


                    CHANGE = false;
                }

                return;

//                comingSoon();
            }
        });

        tvInvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TwoPlayers.this, "Swap", Toast.LENGTH_SHORT).show();

                if (!INVERT) {
                    player1Icon = "0";
                    player2Icon = "X";

                }
                if (INVERT) {
                    player1Icon = "X";
                    player2Icon = "0";

                }
                tvP1id.setText(player1Icon);
                tvP2id.setText(player2Icon);
                INVERT = !INVERT;

            }
        });


        Toast.makeText(this, "Board initialized", Toast.LENGTH_SHORT).show();


    }

    private void setPlayersNames() {

    }

    private void comingSoon() {
        Intent intent = new Intent(TwoPlayers.this, com.dan.tictactoe.Construction.class);
        startActivity(intent);
    }


    private void checkWinner() {
        boolean winnerFound = false;
        for (int i = 0; i < 3; i++) {
            if (boardStatus[0][i] == boardStatus[1][i]
                    && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    Toast.makeText(this, "Player " + player1 + " wins " + (i + 1) + " column", Toast.LENGTH_SHORT).show();
                    player1Score++;
                    endPlay();
                    break;
                } else if (boardStatus[0][i] == 0) {
                    Toast.makeText(this, "Player " + player2 + " wins " + (i + 1) + " column", Toast.LENGTH_SHORT).show();
                    player2Score++;
                    endPlay();
                    break;
                }

            }


        }

        for (int i = 0; i < 3; i++) {
            if (boardStatus[i][0] == boardStatus[i][1]
                    && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    Toast.makeText(this, "Player " + player1 + " wins " + (i + 1) + " row", Toast.LENGTH_SHORT).show();

                    player1Score++;
                    endPlay();

                } else if (boardStatus[i][0] == 0) {
                    Toast.makeText(this, "Player " + player2 + " wins " + (i + 1) + " row", Toast.LENGTH_SHORT).show();
                    player2Score++;
                    endPlay();

                }

            }


        }
        if (boardStatus[1][1] == boardStatus[0][0]
                && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                Toast.makeText(this, "Player " + player1 + " wins " + "First Diagonal", Toast.LENGTH_SHORT).show();

                player1Score++;
                endPlay();
            } else if
                    (boardStatus[0][0] == 0) {
                Toast.makeText(this, "Player " + player2 + " wins " + "First Diagonal", Toast.LENGTH_SHORT).show();
                player2Score++;
                endPlay();
            }
        } else if (boardStatus[0][2] == boardStatus[1][1]
                && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                Toast.makeText(this, "Player " + player1 + " wins " + "Second Diagonal", Toast.LENGTH_SHORT).show();

                player1Score++;
                endPlay();
            } else if (boardStatus[0][2] == 0) {
                Toast.makeText(this, "Player " + player2 + " wins " + "Second Diagonal", Toast.LENGTH_SHORT).show();

                player2Score++;
                endPlay();
            }
        } else {
            boolean empty = false;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (boardStatus[i][j] == -1) {
                        empty = true;
                        break;
                    }
                }
            }
            if (!empty) {
                winnerFound = true;
                Toast.makeText(this, "Hey no winner", Toast.LENGTH_SHORT).show();
                drawScore++;
                endPlay();
            }

        }

//        return winnerFound;

    }

    private void initializeBoardStatus() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardStatus[i][j] = -1;
                button[i][j].setOnClickListener(new ThreeMClickListener(i, j));
                if (!button[i][j].isEnabled()) {
                    button[i][j].setText(" ");
                    button[i][j].setEnabled(true);
                } else {
                    button[i][j].setText(" ");
                }
            }
        }
    }

    private void endPlay() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (button[i][j].isEnabled()) {
                    button[i][j].setEnabled(false);
                }
            }
        }
        onStopClick();

    }

    private void onStopClick() {


        player1 = etPlayer1.getText().toString();
        player2 = etPlayer2.getText().toString();
        tvPlayer1Name.setText("" + player1);
        tvPlayer2Name.setText("" + player2);
        tvDraw.setText("DRAW");
        setUpVisibility();


        tvPlayer1Score.setText("" + player1Score);
        tvPlayer2Score.setText("" + player2Score);
        tvDrawScore.setText("" + drawScore);
        btStart.setText("Replay");
        CHANGE = false;


    }

    private void setUpVisibility() {

        llPlayer1.setVisibility(View.GONE);
        llPlayer2.setVisibility(View.GONE);
        tvDrawScore.setVisibility(View.GONE);
        llSwap.setVisibility(View.GONE);

        tvTurn.setVisibility(View.VISIBLE);

        tvPlayer1Score.setVisibility(View.VISIBLE);
        tvPlayer2Score.setVisibility(View.VISIBLE);
        tvPlayer1Name.setVisibility(View.VISIBLE);
        tvPlayer2Name.setVisibility(View.VISIBLE);
        tvDraw.setVisibility(View.VISIBLE);
        tvDrawScore.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.help:
                comingSoon();
                return true;
            case R.id.share:
                comingSoon();
                return true;
            case R.id.reset_scores:
                comingSoon();
            case R.id.contact:
                comingSoon();
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private class ThreeMClickListener implements View.OnClickListener {
        int x;
        int y;

        public ThreeMClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View v) {


            if (SWITCH == true) {


                if (button[x][y].isEnabled()) {
                    button[x][y].setText(player2Icon);
                    button[x][y].setEnabled(false);
                    boardStatus[x][y] = 0;
                    tvTurn.setText(player1 + " turn to play.");
                    SWITCH = false;
                }

            } else if (SWITCH == false) {

                if (button[x][y].isEnabled()) {
                    button[x][y].setText(player1Icon);
                    button[x][y].setEnabled(false);
                    tvTurn.setText(player2 + " turn to play.");
                    boardStatus[x][y] = 1;
                    SWITCH = true;

                }
            }

            checkWinner();
        }


    }
}
