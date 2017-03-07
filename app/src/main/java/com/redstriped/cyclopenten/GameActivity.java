package com.redstriped.cyclopenten;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.cyclopenten.redstriped.cyclopenten.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private int QuestionNo, AnswerNo, lives, points;
    private Button btn1, btn2, btn3, btn4;
    private String[] questions, answers, answerChoices, hearts, gears;
    private List<Integer> oldQuestions;
    private Random rand, randAnswerChoice;
    private ImageView question, live3, live2, live1, options;
    private boolean handledClick = false;
    private TextView pointsCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        questions = getResources().getStringArray(R.array.Qs);
        hearts = getResources().getStringArray(R.array.Hearts);
        gears = getResources().getStringArray(R.array.Gears);

        answers = getResources().getStringArray(R.array.As);
        oldQuestions = new ArrayList<>();
        QuestionNo = 0;
        AnswerNo = 0;
        lives = 3;
        points = 0;
        rand = new Random();
        randAnswerChoice = new Random();

        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);

        pointsCounter = (TextView) findViewById(R.id.points);
        options = (ImageView) findViewById(R.id.options);
        question = (ImageView) findViewById(R.id.question);
        live3 = (ImageView) findViewById(R.id.live3);
        live2 = (ImageView) findViewById(R.id.live2);
        live1 = (ImageView) findViewById(R.id.live1);
        selectQuestion();


    }

    public void selectQuestion(){

        String i = "";
        char c;
        if(oldQuestions.size()==questions.length || lives==0){
            saveScore();
            Intent intent = new Intent(this, GameOverActivity.class);
            Bundle extras = new Bundle();
            extras.putBoolean("won", true);
            intent.putExtras(extras);
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
        char liveResource = questions[QuestionNo].charAt(2);
        int heartid = Character.getNumericValue(liveResource);
        int id2 = getResources().getIdentifier(hearts[heartid], "drawable", getPackageName());
        int id3 = getResources().getIdentifier(gears[heartid], "drawable", getPackageName());
        options.setImageResource(id3);
        live3.setImageResource(id2);
        live2.setImageResource(id2);
        live1.setImageResource(id2);
        int currentQuestion = QuestionNo * 4;
        answerChoices = new String[]{answers[currentQuestion], answers[currentQuestion + 1], answers[currentQuestion+2], answers[currentQuestion+3]};

        int dummy = randAnswerChoice.nextInt(4);

        btn1.setText(answerChoices[dummy]);
        if(dummy>=3){
            dummy=0;
        }else{
            dummy++;
        }
        btn2.setText(answerChoices[dummy]);
        if(dummy>=3){
            dummy=0;
        }else{
            dummy++;
        }
        btn3.setText(answerChoices[dummy]);
        if(dummy>=3){
            dummy=0;
        }else{
            dummy++;
        }
        btn4.setText(answerChoices[dummy]);

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

            String correctAnswer = answers[AnswerNo];

            if (answer.equals(correctAnswer)) {
                handledClick=true;
                points++;
                int id = getResources().getIdentifier(questions[QuestionNo] + "2", "drawable", getPackageName());
                question.setImageResource(id);
                pointsCounter.setText("Points: " + points);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handledClick=false;
                        selectQuestion();
                    }
                }, 1000);

            } else{
                lives--;
                if(lives==2){
                    live3.setVisibility(View.INVISIBLE);
                }
                if(lives==1){
                    live2.setVisibility(View.INVISIBLE);
                }
                if(lives==0){
                    live1.setVisibility(View.INVISIBLE);
                    saveScore();

                    Intent intent2 = new Intent(this, GameOverActivity.class);
                    Bundle extras = new Bundle();
                    extras.putBoolean("won", false);
                    extras.putInt("points", points);
                    intent2.putExtras(extras);
                    startActivity(intent2);
                    finish();

                }
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
        pointsCounter.setTextColor(dark);
    }

    public void saveScore(){
       SharedPreferences sharedPref = getSharedPreferences("highscore", Context.MODE_PRIVATE);

       SharedPreferences.Editor editor = sharedPref.edit();
       int oldHighscore = sharedPref.getInt("highscore", 0);

       if(points>oldHighscore) {
           editor.putInt("highscore", points);
           editor.apply();
       }
    }
}
