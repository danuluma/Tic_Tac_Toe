package com.dan.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Switch extends AppCompatActivity {
    Button bts, btm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        bts = findViewById(R.id.bts);
        btm = findViewById(R.id.btm);

        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Switch.this, com.dan.tictactoe.SinglePlayer.class);
                startActivity(intent);

            }
        });

        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Switch.this, com.dan.tictactoe.MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
