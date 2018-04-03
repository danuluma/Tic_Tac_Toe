package com.dan.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class SinglePlayer extends AppCompatActivity {

    Button button[][] = new Button[3][3];
    int boardStatus[][] = new int[3][3];

    AI comp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "AAA", Toast.LENGTH_SHORT).show();


        button[0][0] = (Button) findViewById(R.id.button1);
        button[0][1] = (Button) findViewById(R.id.button2);
        button[0][2] = (Button) findViewById(R.id.button3);
        button[1][0] = (Button) findViewById(R.id.button4);
        button[1][1] = (Button) findViewById(R.id.button5);
        button[1][2] = (Button) findViewById(R.id.button6);
        button[2][0] = (Button) findViewById(R.id.button7);
        button[2][1] = (Button) findViewById(R.id.button8);
        button[2][2] = (Button) findViewById(R.id.button9);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j].setOnClickListener(new MyClickListener(i, j));
                if (!button[i][j].isEnabled()) {
                    button[i][j].setText(" ");
                    button[i][j].setEnabled(true);
                }
            }
        }

        initializeBoardStatus();
        Toast.makeText(this, "Board initialized", Toast.LENGTH_SHORT).show();


    }


    private void initializeBoardStatus() {
        for (int i = 0; i < 3; i++) {
            int j;
            for (j = 0; j < 3; j++) {
                boardStatus[i][j] = -1;
                comp = new AI();
            }
//            comp = new AI();


        }


    }



    private boolean checkWinner() {

        boolean winnerFound = false;

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
                    Toast.makeText(this, "Player 0 wins "+(i+1) +" row", Toast.LENGTH_SHORT).show();
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
        } else {
            boolean empty = false;
            for (int i=1; i<3; i++){
                for (int j=1; j<3; j++){
                    if (boardStatus[i][j]==-1){
                        empty=true;
                        break;
                    }
                }
            }
            if (!empty){
                winnerFound=true;
                Toast.makeText(this, "Hey no winner", Toast.LENGTH_SHORT).show();

            }
        }
        {
            boolean empty = false;
            for (int i = 1; i < 3; i++) {
                for (int j = 1; j < 3; j++) {
                    if (boardStatus[i][j] == -1) {
                        empty = true;
                        break;
                    }
                }
            }
            if (!empty) {
                winnerFound = true;

                Toast.makeText(this, "Game over. It's a draw!", Toast.LENGTH_SHORT).show();
            }
        }

        return winnerFound;
    }

    private class AI {
        public void compTurn() {
            if (boardStatus[0][0] == -1 &&
                    ((boardStatus[0][1] == 0 && boardStatus[0][2] == 0) ||
                            (boardStatus[1][1] == 0 && boardStatus[2][2] == 0) ||
                            (boardStatus[1][0] == 0 && boardStatus[2][0] == 0))) {
                markSquare(0, 0);
            } else if (boardStatus[0][1] == -1 &&
                    ((boardStatus[1][1] == 0 && boardStatus[2][1] == 0) ||
                            (boardStatus[0][0] == 0 && boardStatus[0][2] == 0))) {
                markSquare(0, 1);
            } else if (boardStatus[0][2] == -1 &&
                    ((boardStatus[0][0] == 0 && boardStatus[0][1] == 0) ||
                            (boardStatus[2][0] == 0 && boardStatus[1][1] == 0) ||
                            (boardStatus[1][2] == 0 && boardStatus[2][2] == 0))) {
                markSquare(0, 2);


            } else if (boardStatus[1][0] == -1 &&
                    ((boardStatus[1][1] == 0 && boardStatus[1][2] == 0) ||
                            (boardStatus[0][0] == 0 && boardStatus[2][0] == 0))) {
                markSquare(1, 0);
            } else if (boardStatus[1][1] == -1 &&
                    ((boardStatus[0][0] == 0 && boardStatus[2][2] == 0) ||
                            (boardStatus[0][1] == 0 && boardStatus[2][1] == 0) ||
                            (boardStatus[2][0] == 0 && boardStatus[0][2] == 0) ||
                            (boardStatus[1][0] == 0 && boardStatus[1][2] == 0))) {
                markSquare(1, 1);

            } else if (boardStatus[1][2] == -1 &&
                    ((boardStatus[1][0] == 0 && boardStatus[1][1] == 0) ||
                            (boardStatus[0][2] == 0 && boardStatus[2][2] == 0))) {
                markSquare(1, 2);
            } else if (boardStatus[2][0] == -1 &&
                    ((boardStatus[0][0] == 0 && boardStatus[1][0] == 0) ||
                            (boardStatus[2][1] == 0 && boardStatus[2][2] == 0) ||
                            (boardStatus[1][1] == 0 && boardStatus[0][2] == 0))) {
                markSquare(2, 0);
            } else if (boardStatus[2][1] == -1 &&
                    ((boardStatus[0][1] == 0 && boardStatus[1][1] == 0) ||
                            (boardStatus[2][0] == 0 && boardStatus[2][2] == 0))) {
                markSquare(2, 1);


            } else if (boardStatus[2][2] == -1 &&
                    ((boardStatus[0][0] == 0 && boardStatus[1][1] == 0) ||
                            (boardStatus[0][2] == 0 && boardStatus[1][2] == 0) ||
                            (boardStatus[2][0] == 0 && boardStatus[2][1] == 0))) {
                markSquare(2, 2);
            } else {
                Random rand = new Random();

                int a = rand.nextInt(3);
                int b = rand.nextInt(3);
                while (a == -1 || b == -1 || boardStatus[a][b] != -1) {
                    a = rand.nextInt(3);
                    b = rand.nextInt(3);
                }
                markSquare(a, b);
            }
        }


        private void markSquare(int x, int y) {
            button[x][y].setEnabled(false);
            button[x][y].setText("X");
            boardStatus[x][y] = 1;

            checkWinner();
        }
    }

    class MyClickListener implements View.OnClickListener {
        int x;
        int y;

        public MyClickListener(int x, int y) {
            this.y = y;
            this.x = x;

        }

        @Override
        public void onClick(View v) {

            if (button[x][y].isEnabled()) {
                button[x][y].setText("0");
                button[x][y].setEnabled(false);
                boardStatus[x][y] = 0;
//                checkWinner();
                if (!checkWinner()) {
                    comp.compTurn();
                }

            }

        }
    }
}
