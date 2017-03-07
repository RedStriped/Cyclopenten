package com.redstriped.cyclopenten;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.cyclopenten.redstriped.cyclopenten.R;

/**
 * Created by Malte on 23.02.2017.
 */

public class GameOverActivity extends AppCompatActivity {

    private boolean won;
    private int points;
    private TextView tv, currentPoints, bestPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Bundle extras = getIntent().getExtras();
        won = extras.getBoolean("won");
        points = extras.getInt("points");
        tv = (TextView) findViewById(R.id.textView);
        currentPoints = (TextView) findViewById(R.id.currentpoints);
        bestPoints = (TextView) findViewById(R.id.bestpoints);
        SharedPreferences sharedPref = getSharedPreferences("highscore", Context.MODE_PRIVATE);
        int highscore = sharedPref.getInt("highscore", 0);

        if(!won){
            tv.setText("Game Over");
            currentPoints.setText("Points: " + points);
            bestPoints.setText("Best: " + highscore);
        }else{
            currentPoints.setVisibility(View.INVISIBLE);
            bestPoints.setVisibility(View.INVISIBLE);
        }
    }

    public void onSplashPageClick(View view) {
        finish();
    }
}
