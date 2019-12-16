package com.munibasiddiqi.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int answer;
    int score = 0;
    int noOfQuestionsAsked = 0;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    TextView resultTextView;
    ConstraintLayout gameConstraintLayout;
   // public GridLayout answerGridLayout;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;

    ArrayList<Integer>  answersArrayList = new ArrayList<Integer>();

    CountDownTimer timer;


    public void Start(View view) {

        Button goButton = (Button) view;

        goButton.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(View.VISIBLE);
        /*scoreTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        answerGridLayout.setVisibility(View.VISIBLE);*/

        startTimer();
        generateQuestion();


    }

    public void playAgain(View view){

        playAgain.setVisibility(View.INVISIBLE);
        score = 0;
        noOfQuestionsAsked = 0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        startTimer();
        generateQuestion();

    }

    public void startTimer(){
        timer = new CountDownTimer(30000+100,1000) {
            @Override
            public void onTick(long l) {
                Log.i("Seconds Left",String.valueOf(l/1000));
                //UpdateTimer((int) l/1000);

                timerTextView.setText((int)l/1000+"s");
            }

            @Override
            public void onFinish() {
                noOfQuestionsAsked--;
                resultTextView.setText("Your Score: "+score+"/"+noOfQuestionsAsked);
                playAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public void generateQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        int locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer = 0;

        sumTextView.setText(a+ " + "+b);
        answer = a+b;


        noOfQuestionsAsked++;

        answersArrayList.clear();



        for (int i=0;i<4;i++){
            if (i == locationOfCorrectAnswer){
                answersArrayList.add(answer);
            }else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == answer) {
                    incorrectAnswer = rand.nextInt(41);
                }

                answersArrayList.add(incorrectAnswer);

            }

        }


        button0.setText(answersArrayList.get(0).toString());
        button1.setText(answersArrayList.get(1).toString());
        button2.setText(answersArrayList.get(2).toString());
        button3.setText(answersArrayList.get(3).toString());
    }




    public void ChoosingAnswer(View view){


        Button buttonPressed = (Button) view;


        Log.i("button Pressed",buttonPressed.getTag().toString());

        Log.i("button Pressed",buttonPressed.getText().toString());

        if (answer == Integer.parseInt(buttonPressed.getText().toString())) {
            resultTextView.setText("Correct!");
            score++;
        }
        else {
            resultTextView.setText("Wrong!");
        }

        scoreTextView.setText(score+"/"+noOfQuestionsAsked);
        generateQuestion();
    }



    public  void UpdateTimer(int secondsLeft){
        int minutes = (int) secondsLeft/60;

        int seconds = secondsLeft-minutes*60;

        if (seconds<10) {

            timerTextView.setText(minutes + ":0" + seconds);
        }else

            timerTextView.setText(minutes + ":" + seconds);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        playAgain = (Button) findViewById(R.id.playAgainButton);
        gameConstraintLayout = (ConstraintLayout) findViewById(R.id.gameConstraintLayout);
        //answerGridLayout = (GridLayout) findViewById(R.id.answerGridLayout);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);


        /*timer = new CountDownTimer(3000+100,1000) {
            @Override
            public void onTick(long l) {
                Log.i("Seconds Left",String.valueOf(l/1000));
                //UpdateTimer((int) l/1000);

                timerTextView.setText((int)l/1000+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Your Score: "+score+"/"+noOfQuestionsAsked);
                playAgain.setVisibility(View.VISIBLE);

            }
        }.start();*/

      /*  Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(a+ " + "+b);
        answer = a+b;

        noOfQuestionsAsked++;

        int locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer = 0;

        for (int i=0;i<4;i++){
            if (i == locationOfCorrectAnswer){
                answersArrayList.add(answer);
            }else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == answer) {
                    incorrectAnswer = rand.nextInt(41);
                }

                answersArrayList.add(incorrectAnswer);

            }

        }


        button0.setText(answersArrayList.get(0).toString());
        button1.setText(answersArrayList.get(1).toString());
        button2.setText(answersArrayList.get(2).toString());
        button3.setText(answersArrayList.get(3).toString());*/


    }




}
