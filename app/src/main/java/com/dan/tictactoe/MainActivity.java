package com.dan.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    int[][] boardStatus = new int[3][3];
    int TURN_COUNT = 0;
    TextView tv1;
    private boolean PLAYER_X = true;
    boolean COMP = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        tv1 = (TextView) findViewById(R.id.tv1);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        initializeBoardStatus();


    }


    @Override
    public void onClick(View v) {

//        play();

        switch (v.getId()) {

            case R.id.button1:
                if (PLAYER_X) {
                    button1.setText("X");
                    boardStatus[0][0] = 1;

                } else {
                    button1.setText("0");
                    boardStatus[0][0] = 0;
                }
                button1.setEnabled(false);
                break;
            case R.id.button2:
                if (PLAYER_X) {
                    button2.setText("X");
                    boardStatus[0][1] = 1;

                } else {
                    button2.setText("0");
                    boardStatus[0][1] = 0;
                }
                button2.setEnabled(false);
                break;
            case R.id.button3:
                if (PLAYER_X) {
                    button3.setText("X");
                    boardStatus[0][2] = 1;

                } else {
                    button3.setText("0");
                    boardStatus[0][2] = 0;
                }
                button3.setEnabled(false);
                break;
            case R.id.button4:
                if (PLAYER_X) {
                    button4.setText("X");
                    boardStatus[1][0] = 1;

                } else {
                    button4.setText("0");
                    boardStatus[1][0] = 0;
                }
                button4.setEnabled(false);
                break;
            case R.id.button5:
                if (PLAYER_X) {
                    button5.setText("X");
                    boardStatus[1][1] = 1;

                } else {
                    button5.setText("0");
                    boardStatus[1][1] = 0;
                }
                button5.setEnabled(false);
                break;
            case R.id.button6:
                if (PLAYER_X) {
                    button6.setText("X");
                    boardStatus[1][2] = 1;

                } else {
                    button6.setText("0");
                    boardStatus[1][2] = 0;
                }
                button6.setEnabled(false);
                break;
            case R.id.button7:
                if (PLAYER_X) {
                    button7.setText("X");
                    boardStatus[2][0] = 1;

                } else {
                    button7.setText("0");
                    boardStatus[2][0] = 0;
                }
                button7.setEnabled(false);
                break;
            case R.id.button8:
                if (PLAYER_X) {
                    button8.setText("X");
                    boardStatus[2][1] = 1;

                } else {
                    button8.setText("0");
                    boardStatus[2][1] = 0;
                }
                button8.setEnabled(false);
                break;
            case R.id.button9:
                if (PLAYER_X) {
                    button9.setText("X");
                    boardStatus[2][2] = 1;

                } else {
                    button9.setText("0");
                    boardStatus[2][2] = 0;
                }
                button9.setEnabled(false);
                break;

            default:
                break;

        }

        TURN_COUNT++;
        PLAYER_X = !PLAYER_X;


        if (!COMP) {


            if (PLAYER_X) {
                tv1.setText("PLAYER X turn");

            } else {
                tv1.setText("Player 1 turn");
            }

        } else {
            tv1.setText("Your turn");
        }


        if (TURN_COUNT == 9) {
            tv1.setText("Game Draw");
        }

        checkWinner();


    }

    private void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (boardStatus[0][i] == boardStatus[1][i]
                    && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    Toast.makeText(this, "Player X wins " + (i + 1) + " column", Toast.LENGTH_SHORT).show();
                    break;
                } else if (boardStatus[0][i] == 0) {
                    Toast.makeText(this, "Player 0 wins \"+(i+1) +\" column", Toast.LENGTH_SHORT).show();
                    break;
                }

            }


        }

        for (int i = 0; i < 3; i++) {
            if (boardStatus[i][0] == boardStatus[i][1]
                    && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    Toast.makeText(this, "Player X wins " + (i + 1) + " row", Toast.LENGTH_SHORT).show();
                    break;
                } else if (boardStatus[i][0] == 0) {
                    Toast.makeText(this, "Player 0 wins \"+(i+1) +\" row", Toast.LENGTH_SHORT).show();
                    break;
                }

            }


        }
        if (boardStatus[1][1] == boardStatus[0][0]
                && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                Toast.makeText(this, "Player X wins First Diagonal", Toast.LENGTH_SHORT).show();
            } else if
                    (boardStatus[0][0] == 0) {
                Toast.makeText(this, "Player 0 wins First Diagonal", Toast.LENGTH_SHORT).show();
            }
        }

        if (boardStatus[0][2] == boardStatus[1][1]
                && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                Toast.makeText(this, "Player X wins Second Diagonal", Toast.LENGTH_SHORT).show();
            } else if
                    (boardStatus[0][0] == 0) {
                Toast.makeText(this, "Player 0 wins Second Diagonal", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initializeBoardStatus() {
        for (int i = 0; i < 3; i++) {
            int j;
            for (j = 0; j < 3; j++) {
                boardStatus[i][j] = -1;
            }
        }
    }

}
