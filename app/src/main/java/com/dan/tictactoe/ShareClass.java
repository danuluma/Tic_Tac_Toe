package com.dan.tictactoe;

import android.content.Intent;

import static android.support.v4.content.ContextCompat.startActivity;

public class ShareClass {
    private void shareApp() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, "Dan");
        share.putExtra(Intent.EXTRA_TEXT, "I have played this little game and it's AWESOME!!\nFind it at https://www.github.com/danuluma");
        startActivity(Intent.createChooser(share,"Share link using"));
    }

    private void startActivity(Intent share_link_using) {
    }

    public void shareUsing(){
        shareApp();
    }
}
