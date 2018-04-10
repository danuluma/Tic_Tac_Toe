package com.dan.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

public class SinglePlayer extends AppCompatActivity {

    Button button[][] = new Button[3][3];
    int boardStatus[][] = new int[3][3];

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
    boolean winnerFound = false;


    AI comp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

//        Toast.makeText(this, "AAA", Toast.LENGTH_SHORT).show();


        button[0][0] = (Button) findViewById(R.id.button1);
        button[0][1] = (Button) findViewById(R.id.button2);
        button[0][2] = (Button) findViewById(R.id.button3);
        button[1][0] = (Button) findViewById(R.id.button4);
        button[1][1] = (Button) findViewById(R.id.button5);
        button[1][2] = (Button) findViewById(R.id.button6);
        button[2][0] = (Button) findViewById(R.id.button7);
        button[2][1] = (Button) findViewById(R.id.button8);
        button[2][2] = (Button) findViewById(R.id.button9);


//        tv1 = (TextView) findViewById(R.id.tv1);
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

        etPlayer2.setVisibility(View.INVISIBLE);
        etPlayer2.setText("Comp AI");
        tvPlayer2.setText("Comp AI");
        tvPlayer2.setTextSize(24);
        tvPlayer2.setTypeface(Typeface.DEFAULT_BOLD);


        btStart = findViewById(R.id.btStart);
        setSwipeListener();


        btStart.setText("Start");
        btStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (CHANGE == false) {


                    if (!etPlayer1.getText().toString().trim().equals("")) {

                        initializeBoardStatus();
//                    CHANGE = true;
                        onStopClick();
                        btStart.setText("Restart");
                    } else if (etPlayer1.getText().toString().trim().equals("")) {

                        Toast.makeText(SinglePlayer.this, "Please Enter Name First!", Toast.LENGTH_SHORT).show();
                    }


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
                Toast.makeText(SinglePlayer.this, "Swap", Toast.LENGTH_SHORT).show();

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


//        Toast.makeText(this, "Board initialized", Toast.LENGTH_SHORT).show();


    }


    private void initializeBoardStatus() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardStatus[i][j] = -1;
                comp = new AI();
                button[i][j].setOnClickListener(new ThreeSClickListener(i, j));
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
        boolean draw = false;
        for (int i = 0; i < 3; i++) {
            if (boardStatus[0][i] == boardStatus[1][i]
                    && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    Toast.makeText(this, "Player " + player1 + " wins " + (i + 1) + " column", Toast.LENGTH_SHORT).show();
                    player1Score++;
                    winnerFound = true;
                    endPlay();
                    break;
                } else if (boardStatus[0][i] == 0) {
                    Toast.makeText(this, "Player " + player2 + " wins " + (i + 1) + " column", Toast.LENGTH_SHORT).show();
                    player2Score++;
                    winnerFound = true;
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
                    winnerFound = true;
                    endPlay();

                } else if (boardStatus[i][0] == 0) {
                    Toast.makeText(this, "Player " + player2 + " wins " + (i + 1) + " row", Toast.LENGTH_SHORT).show();
                    player2Score++;
                    winnerFound = true;
                    endPlay();

                }

            }


        }
        if (boardStatus[1][1] == boardStatus[0][0]
                && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                Toast.makeText(this, "Player " + player1 + " wins " + "First Diagonal", Toast.LENGTH_SHORT).show();

                player1Score++;
                winnerFound = true;
                endPlay();
            } else if
                    (boardStatus[0][0] == 0) {
                Toast.makeText(this, "Player " + player2 + " wins " + "First Diagonal", Toast.LENGTH_SHORT).show();
                player2Score++;
                winnerFound = true;
                endPlay();
            }
        } else if (boardStatus[0][2] == boardStatus[1][1]
                && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                Toast.makeText(this, "Player " + player1 + " wins " + "Second Diagonal", Toast.LENGTH_SHORT).show();

                player1Score++;
                winnerFound = true;
                endPlay();
            } else if (boardStatus[0][2] == 0) {
                Toast.makeText(this, "Player " + player2 + " wins " + "Second Diagonal", Toast.LENGTH_SHORT).show();

                player2Score++;
                winnerFound = true;
                endPlay();
            }
        } else {
            boolean empty = false;
            try {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (boardStatus[i][j] == -1) {
                            empty = true;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if (!empty) {
                draw = true;
                Toast.makeText(this, "Hey no winner", Toast.LENGTH_SHORT).show();
                drawScore++;
                winnerFound = true;
                endPlay();
            }

        }

        return draw;

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
                Intent intent = new Intent(SinglePlayer.this, com.dan.tictactoe.MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.share:
                shareIntent();

                return true;
            case R.id.reset_scores:
                resetScores();
                return true;

            case R.id.about:
                about();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareIntent() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, "Dan");
        share.putExtra(Intent.EXTRA_TEXT, "I've played this game and it's AWESOME!!\nFind it at https://github.com/danuluma");
        startActivity(Intent.createChooser(share, "Share link using"));

    }

    private void resetScores() {

        player1Score = 0;
        player2Score = 0;
        drawScore = 0;
        initializeBoardStatus();
        onStopClick();
        btStart.setText("Start");
    }

    private void comingSoon() {
        Intent intent = new Intent(SinglePlayer.this, com.dan.tictactoe.Construction.class);
        startActivity(intent);
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
        return;

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

    private void setSwipeListener() {
        try {
            SwipeListener swipeListener = new SwipeListener(SinglePlayer.this) {
                @Override
                public void onSwipeRight() {

                    Intent intent = new Intent(SinglePlayer.this, com.dan.tictactoe.FiveBoardSingle.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onSwipeTop() {
                    Intent intent = new Intent(SinglePlayer.this, com.dan.tictactoe.TwoPlayers.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onSwipeLeft() {
                    Intent intent = new Intent(SinglePlayer.this, com.dan.tictactoe.FiveBoardSingle.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onSwipeBottom() {
                    Intent intent = new Intent(SinglePlayer.this, com.dan.tictactoe.TwoPlayers.class);
                    startActivity(intent);
                    finish();
                }
            };
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void about() {
        AlertDialog about = new AlertDialog.Builder(SinglePlayer.this).create();
        about.setTitle("About");
        about.setMessage("Tic Tac Toe by Dan\n" +
                "github.com/danuluma/Tic_Tac_Toe\nExplore my other projects on Git\n" +
                "Copyright:2018\nAll Rights Reserved");
        about.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        about.setButton(AlertDialog.BUTTON_POSITIVE, "GITHUB",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {


                            Uri uri = Uri.parse("http://github.com/danuluma/Tic_Tac_Toe");
                            Intent intent = new Intent();
                            intent.setData(uri);
                            intent.setAction(Intent.ACTION_VIEW);
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(SinglePlayer.this, "No Browser Installed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        about.show();
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
            button[x][y].setText(player2Icon);
            boardStatus[x][y] = 0;

        }
    }

    class ThreeSClickListener implements View.OnClickListener {
        int x;
        int y;

        public ThreeSClickListener(int x, int y) {
            this.y = y;
            this.x = x;

        }

        @Override
        public void onClick(View v) {

            if (button[x][y].isEnabled()) {
                button[x][y].setText(player1Icon);
                button[x][y].setEnabled(false);
                boardStatus[x][y] = 1;
                tvTurn.setText(player2 + " thinking...");


                checkWinner();


                if (!checkWinner()) {
                    try {


                        comp.compTurn();
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(SinglePlayer.this, player2 + " Interrupted", Toast.LENGTH_SHORT).show();
                    }

                    tvTurn.setText(player1 + " turn to play.");

                    checkWinner();
                }


            }

        }
    }

}







