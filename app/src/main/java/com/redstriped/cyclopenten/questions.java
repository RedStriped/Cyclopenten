package com.redstriped.cyclopenten;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.cyclopenten.redstriped.cyclopenten.R;

public class questions extends AppCompatActivity {

    private int QuestionNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        String[] questions = getResources().getStringArray(R.array.Qs);
        ImageView t = (ImageView) findViewById(R.id.question);
        int id = getResources().getIdentifier(questions[QuestionNo], "drawable", getPackageName());
        t.setImageResource(id);
        //setBackgroundResource(R.drawable. + questions[QuestionNo]);
        findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
        findViewById(R.id.correctornot).setVisibility(View.INVISIBLE);
    }


}
