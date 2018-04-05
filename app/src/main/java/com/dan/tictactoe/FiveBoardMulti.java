package com.dan.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FiveBoardMulti extends AppCompatActivity {

    Button button[][] = new Button[5][5];
    int boardStatus[][] = new int[5][5];
    public boolean SWITCH = true;

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

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                button[i][j].setOnClickListener(new FiveClickListener(i, j));
                if (!button[i][j].isEnabled()) {
                    button[i][j].setText(" ");
                    button[i][j].setEnabled(true);
                }else {
                    button[i][j].setText(" ");
                }
            }
        }

        initializeBoardStatus();
        Toast.makeText(this, "Board initialized", Toast.LENGTH_SHORT).show();






    }

    private void initializeBoardStatus() {
        for (int i = 0; i < 5; i++) {
        int j;
        for (j = 0; j < 5; j++) {
            boardStatus[i][j] = -1;
//                comp = new AI();
        }
//            comp = new AI();


    }
    }

    private class FiveClickListener implements View.OnClickListener {

        int x;
        int y;

        public FiveClickListener(int x, int y) {
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

            }

            else if (SWITCH == false){

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

    private void checkWinner() {
        for (int i = 0; i < 5; i++) {
            if (boardStatus[0][i] == boardStatus[1][i]
                    && boardStatus[0][i] == boardStatus[2][i]
                    && boardStatus[0][i] == boardStatus[3][i]
                    && boardStatus[0][i] == boardStatus[4][i]) {
                if (boardStatus[0][i] == 1) {
                    Toast.makeText(this, "Player X wins " + (i + 1) + " column", Toast.LENGTH_SHORT).show();
                    break;
                } else if (boardStatus[0][i] == 0) {
                    Toast.makeText(this, "Player 0 wins \"+(i+1) +\" column", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(this, "Player X wins " + (i + 1) + " row", Toast.LENGTH_SHORT).show();
                    break;
                } else if (boardStatus[i][0] == 0) {
                    Toast.makeText(this, "Player 0 wins \"+(i+1) +\" row", Toast.LENGTH_SHORT).show();
                    break;
                }

            }


        }
        if (boardStatus[1][1] == boardStatus[0][0]
                && boardStatus[0][0] == boardStatus[2][2]
                && boardStatus[0][0] == boardStatus[3][3]
                && boardStatus[0][0] == boardStatus[4][4]) {
            if (boardStatus[0][0] == 1) {
                Toast.makeText(this, "Player X wins First Diagonal", Toast.LENGTH_SHORT).show();
            } else if
                    (boardStatus[0][0] == 0) {
                Toast.makeText(this, "Player 0 wins First Diagonal", Toast.LENGTH_SHORT).show();
            }
        }

        if (boardStatus[0][4] == boardStatus[1][3]
                && boardStatus[0][4] == boardStatus[2][2]
                && boardStatus[0][4] == boardStatus[3][1]
                && boardStatus[0][4] == boardStatus[4][0]) {
            if (boardStatus[0][4] == 1) {
                Toast.makeText(this, "Player X wins Second Diagonal", Toast.LENGTH_SHORT).show();
            } else if
                    (boardStatus[0][4] == 0) {
                Toast.makeText(this, "Player 0 wins Second Diagonal", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
