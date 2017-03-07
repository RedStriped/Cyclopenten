package com.redstriped.cyclopenten;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.cyclopenten.redstriped.cyclopenten.R;

/**
 * Created by Malte on 23.02.2017.
 */

public class GameOverActivity extends AppCompatActivity {

    private boolean won;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Bundle b = getIntent().getExtras();
        won = getIntent().getExtras().getBoolean("won");
        tv = (TextView) findViewById(R.id.textView);
        if(!won){
            tv.setText("Game Over");
        }
    }

    public void onSplashPageClick(View view) {
        finish();
    }
}
