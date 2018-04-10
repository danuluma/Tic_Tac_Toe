package com.dan.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivUser1, ivUser2, ivUser3, ivUser4;
    ImageView ivUser[] = new ImageView[4];
    Button btSelect;
    int viewNumber = 1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivUser[0] = findViewById(R.id.ivUser1);
        ivUser[1] = findViewById(R.id.ivUser2);
        ivUser[2] = findViewById(R.id.ivUser3);
        ivUser[3] = findViewById(R.id.ivUser4);
        btSelect = findViewById(R.id.btSelect);


        for (int i = 0; i < 4; i++) {

//            viewNumber = i+1;
            hideView();
            showThisView();

            ivUser[i].setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {


                public void onSwipeRight() {



                    if (viewNumber == 1) {



//                        ivUser.setImageResource(R.drawable.single5);

                        viewNumber = 2;
                        hideView();
                        showThisView();

                        ivUser[viewNumber].setBackground(getResources().getDrawable(R.drawable.single5));

                        Toast.makeText(MainActivity.this, "view"+viewNumber, Toast.LENGTH_SHORT).show();

                    }
                    if (viewNumber == 2) {

//                        ivUser.setImageResource(R.drawable.single3);
                        viewNumber = 1;
                        hideView();
                        showThisView();

                        ivUser[viewNumber].setBackground(getResources().getDrawable(R.drawable.single3));


                        Toast.makeText(MainActivity.this, "View"+viewNumber, Toast.LENGTH_SHORT).show();
                    }
                    if (viewNumber == 3) {

//                        ivUser.setImageResource(R.drawable.multi5);
                        viewNumber = 4;
                        hideView();
                        showThisView();
                    }
                    if (viewNumber == 4) {

//                        ivUser.setImageResource(R.drawable.multi3);
                        viewNumber = 3;
                        hideView();
                        showThisView();
                    }



                }

                public void onSwipeLeft() {



                    if (viewNumber == 1) {


//                        ivUser.setImageResource(R.drawable.single5);
                        viewNumber = 2;
                        hideView();
                        showThisView();

                        ivUser[viewNumber].setBackground(getResources().getDrawable(R.drawable.single5));

                        Toast.makeText(MainActivity.this, "single5", Toast.LENGTH_SHORT).show();

                    }
                    if (viewNumber == 2) {

//                        ivUser.setImageResource(R.drawable.single3);
                        viewNumber = 1;
                        hideView();
                        showThisView();
                    }
                    if (viewNumber == 3) {

//                        ivUser.setImageResource(R.drawable.multi5);
                        viewNumber = 4;
                        hideView();
                        showThisView();
                    }
                    if (viewNumber == 4) {

//                        ivUser.setImageResource(R.drawable.multi3);
                        viewNumber = 3;
                        hideView();
                        showThisView();
                    }



                }

                public void onSwipeTop() {
                    hideView();

                    if (viewNumber == 1) {

                        ivUser[viewNumber].setBackground(getResources().getDrawable(R.drawable.multi3));


//                        ivUser.setImageResource(R.drawable.multi3);
                        viewNumber = 3;
                    }
                    if (viewNumber == 2) {

//                        ivUser.setImageResource(R.drawable.multi5);
                        viewNumber = 4;
                    }
                    if (viewNumber == 3) {

//                        ivUser.setImageResource(R.drawable.single3);
                        viewNumber = 1;
                    }
                    if (viewNumber == 4) {

//                        ivUser.setImageResource(R.drawable.single5);
                        viewNumber = 2;
                    }

                    showThisView();
                }

                public void onSwipeBottom() {
                    hideView();

                    if (viewNumber == 1) {

//                        ivUser.setImageResource(R.drawable.multi3);
                        viewNumber = 3;
                        Toast.makeText(MainActivity.this, "multi3", Toast.LENGTH_SHORT).show();

                    }
                    if (viewNumber == 2) {

//                        ivUser.setImageResource(R.drawable.multi5);
                        viewNumber = 4;
                    }
                    if (viewNumber == 3) {

//                        ivUser.setImageResource(R.drawable.single3);
                        viewNumber = 1;
                    }
                    if (viewNumber == 4) {

//                        ivUser.setImageResource(R.drawable.single5);
                        viewNumber = 2;
                    }
                    showThisView();

                }


                public void onTap() {
//                Toast.makeText(MainActivity.this, "Tap", Toast.LENGTH_SHORT).show();
                }


            });

        }


        btSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (viewNumber) {
                    case 1:
                        Intent intent = new Intent(MainActivity.this, com.dan.tictactoe.SinglePlayer.class);
                        startActivity(intent);
                    case 2:
                        Intent intent1 = new Intent(MainActivity.this, com.dan.tictactoe.FiveBoardSingle.class);
                        startActivity(intent1);
                    case 3:
                        Intent intent2 = new Intent(MainActivity.this, com.dan.tictactoe.TwoPlayers.class);
                        startActivity(intent2);
                    case 4:
                        Intent intent3 = new Intent(MainActivity.this, com.dan.tictactoe.FiveBoardMulti.class);
                        startActivity(intent3);
                    default:
                        break;


                }


                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showThisView() {
        ivUser[viewNumber].setVisibility(View.VISIBLE);
    }

    private void hideView() {
        for (int i = 0; i < 4 && i!=viewNumber; i++){
            ivUser[i].setVisibility(View.GONE);
        }
    }
}
