package com.dan.tictactoe;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    TextView tvPlayer1Name, tvPlayer2Name, tvPlayer1Score, tvPlayer2Score, tvDraw, tvDrawScore;
//    String player1, player2;
    int player1Score, player2Score, drawScore = 0;
    boolean CHANGE = false;
EditText etTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        tvDraw=findViewById(R.id.tvDraw);
        tvDrawScore = findViewById(R.id.tvDrawScore);

        btStart = findViewById(R.id.btStart);
        etTemp = findViewById(R.id.etTemp);


        btStart.setText("Start");
        btStart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                if (CHANGE==false) {
                    String player1 = etPlayer1.getText().toString();
                    String player2 = etPlayer2.getText().toString();
                    tvPlayer1Name.setText("" + player1);
                    tvPlayer2Name.setText("" + player2);

                    llPlayer1.setVisibility(View.GONE);
                    llPlayer2.setVisibility(View.GONE);

                    tvPlayer1Score.setVisibility(View.VISIBLE);
                    tvPlayer2Score.setVisibility(View.VISIBLE);
                    tvPlayer1Name.setVisibility(View.VISIBLE);
                    tvPlayer2Name.setVisibility(View.VISIBLE);
                    tvDraw.setVisibility(View.VISIBLE);
                    tvDrawScore.setVisibility(View.VISIBLE);


                    tvPlayer1Score.setText("" + player1Score);
                    tvPlayer2Score.setText("" + player2Score);
                    tvDrawScore.setText(""+drawScore);


                    btStart.setText("Stop");
                    CHANGE = true;
                }

                else if (CHANGE==true) {
                    btStart.setText("Start");
                    CHANGE = false;
                }

                return;

//                comingSoon();
            }
        });


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j].setOnClickListener(new ThreeMClickListener(i, j));
                if (!button[i][j].isEnabled()) {
                    button[i][j].setText(" ");
                    button[i][j].setEnabled(true);
                } else {
                    button[i][j].setText(" ");
                }
            }
        }

        initializeBoardStatus();
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
                    Toast.makeText(this, "Player X wins " + (i + 1) + " column", Toast.LENGTH_SHORT).show();
                    endPlay();
                    break;
                } else if (boardStatus[0][i] == 0) {
                    Toast.makeText(this, "Player 0 wins " + (i + 1) + " column", Toast.LENGTH_SHORT).show();
                    endPlay();
                    break;
                }

            }


        }

        for (int i = 0; i < 3; i++) {
            if (boardStatus[i][0] == boardStatus[i][1]
                    && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    Toast.makeText(this, "Player X wins " + (i + 1) + " row", Toast.LENGTH_SHORT).show();
                    endPlay();
                    break;
                } else if (boardStatus[i][0] == 0) {
                    Toast.makeText(this, "Player 0 wins " + (i + 1) + " row", Toast.LENGTH_SHORT).show();
                    endPlay();
                    break;
                }

            }


        }
        if (boardStatus[1][1] == boardStatus[0][0]
                && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                Toast.makeText(this, "Player X wins First Diagonal", Toast.LENGTH_SHORT).show();
                endPlay();
            } else if
                    (boardStatus[0][0] == 0) {
                Toast.makeText(this, "Player 0 wins First Diagonal", Toast.LENGTH_SHORT).show();
                endPlay();
            }
        } else if (boardStatus[0][2] == boardStatus[1][1]
                && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                Toast.makeText(this, "Player X wins Second Diagonal", Toast.LENGTH_SHORT).show();
                endPlay();
            } else if
                    (boardStatus[0][0] == 0) {
                Toast.makeText(this, "Player 0 wins Second Diagonal", Toast.LENGTH_SHORT).show();
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
                endPlay();
            }

        }

//        return winnerFound;

    }

    private void initializeBoardStatus() {
        for (int i = 0; i < 3; i++) {
            int j;
            for (j = 0; j < 3; j++) {
                boardStatus[i][j] = -1;
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
                    button[x][y].setText("0");
                    button[x][y].setEnabled(false);
                    boardStatus[x][y] = 0;
                    SWITCH = false;
                }

            } else if (SWITCH == false) {

                if (button[x][y].isEnabled()) {
                    button[x][y].setText("X");
                    button[x][y].setEnabled(false);
                    boardStatus[x][y] = 1;
                    SWITCH = true;

                }
            }

            checkWinner();
        }


    }
}
