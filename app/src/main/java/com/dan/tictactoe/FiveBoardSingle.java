package com.dan.tictactoe;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.Random;

public class FiveBoardSingle extends AppCompatActivity {
    Button button[][] = new Button[5][5];
    int boardStatus[][] = new int[5][5];

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
    Boolean INVERT = false;
    LinearLayout llSwap;
    TextView tvTurn;
    AI2 comp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        button[0][0] = (Button) findViewById(R.id.button1);
        button[0][1] = (Button) findViewById(R.id.button2);
        button[0][2] = (Button) findViewById(R.id.button3);
        button[0][3] = (Button) findViewById(R.id.button4);
        button[0][4] = (Button) findViewById(R.id.button5);

        button[1][0] = (Button) findViewById(R.id.button6);
        button[1][1] = (Button) findViewById(R.id.button7);
        button[1][2] = (Button) findViewById(R.id.button8);
        button[1][3] = (Button) findViewById(R.id.button9);
        button[1][4] = (Button) findViewById(R.id.button10);

        button[2][0] = (Button) findViewById(R.id.button11);
        button[2][1] = (Button) findViewById(R.id.button12);
        button[2][2] = (Button) findViewById(R.id.button13);
        button[2][3] = (Button) findViewById(R.id.button14);
        button[2][4] = (Button) findViewById(R.id.button15);

        button[3][0] = (Button) findViewById(R.id.button16);
        button[3][1] = (Button) findViewById(R.id.button17);
        button[3][2] = (Button) findViewById(R.id.button18);
        button[3][3] = (Button) findViewById(R.id.button19);
        button[3][4] = (Button) findViewById(R.id.button20);

        button[4][0] = (Button) findViewById(R.id.button21);
        button[4][1] = (Button) findViewById(R.id.button22);
        button[4][2] = (Button) findViewById(R.id.button23);
        button[4][3] = (Button) findViewById(R.id.button24);
        button[4][4] = (Button) findViewById(R.id.button25);

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
                Toast.makeText(FiveBoardSingle.this, "Swap", Toast.LENGTH_SHORT).show();

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


//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                button[i][j].setOnClickListener(new FiveClickListener(i, j));
//                if (!button[i][j].isEnabled()) {
//                    button[i][j].setText(" ");
//                    button[i][j].setEnabled(true);
//                }else {
//                    button[i][j].setText(" ");
//                }
//            }
//        }
//
//        initializeBoardStatus();
//        Toast.makeText(this, "Board initialized", Toast.LENGTH_SHORT).show();


    }

    private void initializeBoardStatus() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardStatus[i][j] = -1;
                comp = new AI2();
                button[i][j].setOnClickListener(new FiveSClickListener(i, j));
                if (!button[i][j].isEnabled()) {
                    button[i][j].setText(" ");
                    button[i][j].setEnabled(true);
                } else {
                    button[i][j].setText(" ");
                }
            }
        }
    }

    private boolean checkWinner() {
        boolean winnerFound = false;

        for (int i = 0; i < 5; i++) {
            if (boardStatus[0][i] == boardStatus[1][i]
                    && boardStatus[0][i] == boardStatus[2][i]
                    && boardStatus[0][i] == boardStatus[3][i]
                    && boardStatus[0][i] == boardStatus[4][i]) {
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

        for (int i = 0; i < 5; i++) {
            if (boardStatus[i][0] == boardStatus[i][1]
                    && boardStatus[i][0] == boardStatus[i][2]
                    && boardStatus[i][0] == boardStatus[i][3]
                    && boardStatus[i][0] == boardStatus[i][4]) {
                if (boardStatus[i][0] == 1) {
                    Toast.makeText(this, "Player " + player1 + " wins " + (i + 1) + " row", Toast.LENGTH_SHORT).show();
                    player1Score++;
                    endPlay();
                    break;
                } else if (boardStatus[i][0] == 0) {
                    Toast.makeText(this, "Player " + player2 + " wins " + (i + 1) + " row", Toast.LENGTH_SHORT).show();
                    player2Score++;
                    endPlay();
                    break;
                }

            }


        }
        if (boardStatus[1][1] == boardStatus[0][0]
                && boardStatus[0][0] == boardStatus[2][2]
                && boardStatus[0][0] == boardStatus[3][3]
                && boardStatus[0][0] == boardStatus[4][4]) {
            if (boardStatus[0][0] == 1) {
                Toast.makeText(this, "Player " + player1 + " wins First Diagonal", Toast.LENGTH_SHORT).show();
                player1Score++;
                endPlay();
            } else if
                    (boardStatus[0][0] == 0) {
                Toast.makeText(this, "Player " + player2 + " wins First Diagonal", Toast.LENGTH_SHORT).show();
                player2Score++;
                endPlay();
            }
        }

        if (boardStatus[0][4] == boardStatus[1][3]
                && boardStatus[0][4] == boardStatus[2][2]
                && boardStatus[0][4] == boardStatus[3][1]
                && boardStatus[0][4] == boardStatus[4][0]) {
            if (boardStatus[0][4] == 1) {
                Toast.makeText(this, "Player " + player1 + " wins Second Diagonal", Toast.LENGTH_SHORT).show();
                player1Score++;
                endPlay();
            } else if
                    (boardStatus[0][4] == 0) {
                Toast.makeText(this, "Player " + player2 + " wins Second Diagonal", Toast.LENGTH_SHORT).show();
                player2Score++;
                endPlay();
            }
        } else {
            boolean empty = false;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
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

        return winnerFound;
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
                resetScores();
                return true;
            case R.id.contact:
                comingSoon();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void comingSoon() {
        Intent intent = new Intent(FiveBoardSingle.this, com.dan.tictactoe.Construction.class);
        startActivity(intent);
    }

    private void endPlay() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (button[i][j].isEnabled()) {
                    button[i][j].setEnabled(false);
                }
            }
        }
        onStopClick();

    }

    private void onStopClick() {


        player1 = etPlayer1.getText().toString();
        player2 = "COMP";
//        player2 = etPlayer2.getText().toString();
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

    private class FiveSClickListener implements View.OnClickListener {
        int x;
        int y;

        public FiveSClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View v) {
            if (button[x][y].isEnabled()) {
                button[x][y].setText(player1Icon);
                button[x][y].setEnabled(false);
                boardStatus[x][y] = 1;
                tvTurn.setText(player2 + " turn to play.");

                checkWinner();
                if (!checkWinner()) {
                    comp.compTurn();
                    tvTurn.setText(player1 + " turn to play.");


                    checkWinner();
                }


            }

        }
    }

    private class AI2 {
        public void compTurn() {
            {
                Random rand = new Random();

                int a = rand.nextInt(5);
                int b = rand.nextInt(5);
                while (a == -1 || b == -1 || boardStatus[a][b] != -1) {
                    a = rand.nextInt(5);
                    b = rand.nextInt(5);
                }
                markSquare(a, b);
            }
        }

        private void markSquare(int x, int y) {
            button[x][y].setEnabled(false);
            button[x][y].setText(player2Icon);
            boardStatus[x][y] = 0;

        }
    }
    private void resetScores() {

        player1Score = 0;
        player2Score = 0;
        drawScore = 0;
        initializeBoardStatus();
        onStopClick();
        btStart.setText("Start");
    }
}
