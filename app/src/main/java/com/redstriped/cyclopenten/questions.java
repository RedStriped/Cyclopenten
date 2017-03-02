package com.redstriped.cyclopenten;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.cyclopenten.redstriped.cyclopenten.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class questions extends AppCompatActivity {

    private int QuestionNo;
    private int AnswerNo;
    private Button btn1, btn2, btn3, btn4;
    private String[] questions, answers, answerChoices;
    private List<Integer> oldQuestions;
    private Random rand;
    private ImageView question;
    private boolean handledClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        questions = getResources().getStringArray(R.array.Qs);
        String[] questions2 = Arrays.copyOfRange(questions, 0, 33);
        answers = getResources().getStringArray(R.array.As);
        oldQuestions = new ArrayList<>();
        QuestionNo = 0;
        AnswerNo = 0;
        rand = new Random();

        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);

        question = (ImageView) findViewById(R.id.question);

        selectQuestion();
        //setBackgroundResource(R.drawable. + questions[QuestionNo]);

    }

    public void selectQuestion(){

        String i = "";
        char c;
        if(oldQuestions.size()==questions.length){
            Intent intent = new Intent(this, finish.class);
            startActivity(intent);
            finish();
        }
        while(oldQuestions.contains(QuestionNo)){
            QuestionNo = rand.nextInt(questions.length);
        }

        oldQuestions.add(QuestionNo);
        AnswerNo = (QuestionNo*4);
        int id = getResources().getIdentifier(questions[QuestionNo], "drawable", getPackageName());
        question.setImageResource(id);

        int currentQuestion = QuestionNo * 4;
        //answerChoices = new String[]{answers[currentQuestion], answers[currentQuestion + 1], answers[currentQuestion+2], answers[currentQuestion+3]};
        btn1.setText(answers[currentQuestion]);
        btn2.setText(answers[currentQuestion+1]);
        btn3.setText(answers[currentQuestion+2]);
        btn4.setText(answers[currentQuestion+3]);

        findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
        findViewById(R.id.correctornot).setVisibility(View.INVISIBLE);


        i = questions[QuestionNo];
        c = i.charAt(2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            colorConf(c);
        }else{
            colorConfOld(c);
        }
        //QuestionNo += 1;
    }

    public void onSubmitClick(View view){
        if(!handledClick) {
            Button pressedBtn = (Button) view;
            String answer = pressedBtn.getText().toString();
            answer.toLowerCase();
            //for debug clear later
       /* Toast toasty = Toast.makeText(getApplicationContext(), answers[AnswerNo], Toast.LENGTH_SHORT);
        toasty.show();*/
            String correctAnswer = answers[AnswerNo];

            if (answer.equals(correctAnswer)) {
                handledClick=true;
                int id = getResources().getIdentifier(questions[QuestionNo] + "2", "drawable", getPackageName());
                question.setImageResource(id);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handledClick=false;
                        selectQuestion();
                    }
                }, 1000);
                //QuestionNo += 1;
                //AnswerNo += 4;
            }
        }

    }

    public void colorConfOld(char i){
        int b = Character.getNumericValue(i);

        switch(b) {
            case 0:
                ColorConf2(getResources().getColor(R.color.p0Primary),getResources().getColor(R.color.p0Accent),getResources().getColor(R.color.p0PrimaryDark));
                break;
            case 1:
                ColorConf2(getResources().getColor(R.color.p1Primary),getResources().getColor(R.color.p1Accent),getResources().getColor(R.color.p1PrimaryDark));
                break;
            case 2:
                ColorConf2(getResources().getColor(R.color.p2Primary),getResources().getColor(R.color.p2Accent),getResources().getColor(R.color.p2PrimaryDark));
                break;
            case 3:
                ColorConf2(getResources().getColor(R.color.p3Primary),getResources().getColor(R.color.p3Accent),getResources().getColor(R.color.p3PrimaryDark));
                break;
            case 4:
                ColorConf2(getResources().getColor(R.color.p4Primary),getResources().getColor(R.color.p4Accent),getResources().getColor(R.color.p4PrimaryDark));
                break;
            case 5:
                ColorConf2(getResources().getColor(R.color.p5Primary),getResources().getColor(R.color.p5Accent),getResources().getColor(R.color.p5PrimaryDark));
                break;
            case 6:
                ColorConf2(getResources().getColor(R.color.p6Primary),getResources().getColor(R.color.p6Accent),getResources().getColor(R.color.p6PrimaryDark));
                break;
            case 7:
                ColorConf2(getResources().getColor(R.color.p7Primary),getResources().getColor(R.color.p7Accent),getResources().getColor(R.color.p7PrimaryDark));
                break;
            case 8:
                ColorConf2(getResources().getColor(R.color.p8Primary),getResources().getColor(R.color.p8Accent),getResources().getColor(R.color.p8PrimaryDark));
                break;
            case 9:
                ColorConf2(getResources().getColor(R.color.p9Primary),getResources().getColor(R.color.p9Accent),getResources().getColor(R.color.p9PrimaryDark));
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void colorConf(char i){

        int b = Character.getNumericValue(i);

        switch(b) {
            case 0:
                ColorConf2(getBaseContext().getResources().getColor(R.color.p0Primary, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p0Accent, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p0PrimaryDark, getBaseContext().getTheme()));
                break;
            case 1:
                ColorConf2(getBaseContext().getResources().getColor(R.color.p1Primary, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p1Accent, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p1PrimaryDark, getBaseContext().getTheme()));
                break;
            case 2:
                ColorConf2(getBaseContext().getResources().getColor(R.color.p2Primary, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p2Accent, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p2PrimaryDark, getBaseContext().getTheme()));
                break;
            case 3:
                ColorConf2(getBaseContext().getResources().getColor(R.color.p3Primary, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p3Accent, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p3PrimaryDark, getBaseContext().getTheme()));
                break;
            case 4:
                ColorConf2(getBaseContext().getResources().getColor(R.color.p4Primary, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p4Accent, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p4PrimaryDark, getBaseContext().getTheme()));
                break;
            case 5:
                ColorConf2(getBaseContext().getResources().getColor(R.color.p5Primary, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p5Accent, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p5PrimaryDark, getBaseContext().getTheme()));
                break;
            case 6:
                ColorConf2(getBaseContext().getResources().getColor(R.color.p6Primary, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p6Accent, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p6PrimaryDark, getBaseContext().getTheme()));
                break;
            case 7:
                ColorConf2(getBaseContext().getResources().getColor(R.color.p7Primary, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p7Accent, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p7PrimaryDark, getBaseContext().getTheme()));
                break;
            case 8:
                ColorConf2(getBaseContext().getResources().getColor(R.color.p8Primary, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p8Accent, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p8PrimaryDark, getBaseContext().getTheme()));
                break;
            case 9:
                ColorConf2(getBaseContext().getResources().getColor(R.color.p9Primary, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p9Accent, getBaseContext().getTheme()),getBaseContext().getResources().getColor(R.color.p9PrimaryDark, getBaseContext().getTheme()));
                break;
        }
    }

    /**
     * Sets the color schema for the current question
     * @param prim
     * @param light
     * @param dark
     */
    public void ColorConf2(int prim, int light, int dark){
        LinearLayout back = (LinearLayout) findViewById(R.id.activity_questions);
        TextView qtext = (TextView) findViewById(R.id.questiontext);
        TextView corn = (TextView) findViewById(R.id.correctornot);

        back.setBackgroundColor(prim);
        qtext.setTextColor(dark);
        btn1.setBackgroundColor(dark);
        btn1.setTextColor(light);
        btn2.setBackgroundColor(dark);
        btn2.setTextColor(light);
        btn3.setBackgroundColor(dark);
        btn3.setTextColor(light);
        btn4.setBackgroundColor(dark);
        btn4.setTextColor(light);
        corn.setTextColor(dark);

    }
}
