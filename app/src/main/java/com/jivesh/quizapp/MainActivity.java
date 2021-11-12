package com.jivesh.quizapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TextView mTxtQuestion;
    private Button btnTrue;
    private Button btnFalse;
    private int mQuestionIndex;
    private int mQuizQuestion;
    private ProgressBar progressBar;
    private TextView txtQuizStats;
    private int UScore;


    private  QuizModel[] questions = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,true),
            new QuizModel(R.string.q3,false),
            new QuizModel(R.string.q4,true),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,false),
            new QuizModel(R.string.q10,true),

    };
    final int USER_PROGRESS= (int) Math.ceil( 100/questions.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtQuestion=  findViewById(R.id.txtQuestion) ;

        QuizModel q1 = questions[mQuestionIndex];
        mQuizQuestion = q1.getmQuestion();

        mTxtQuestion.setText(mQuizQuestion);

        txtQuizStats = findViewById(R.id.txtQuizStats);
        progressBar = findViewById(R.id.quizPB);

        btnTrue= findViewById(R.id.btnTrue);
        btnFalse= findViewById(R.id.btnFalse);

       btnTrue.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               evaluateAnswer(true);
               changeQuestionOnButtonClick();


           }
       });

       btnFalse.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               evaluateAnswer(false);
               changeQuestionOnButtonClick();

           }
       });




    }

    private void changeQuestionOnButtonClick(){
        mQuestionIndex = (mQuestionIndex + 1) % 10;
        if(mQuestionIndex == 0){
            AlertDialog.Builder alertq = new AlertDialog.Builder(MainActivity.this);
            alertq.setCancelable(false);
            alertq.setTitle("The Quiz Is Finished !!!..Thank You for Attempting");
            alertq.setMessage("Your score is "+UScore);
            alertq.setPositiveButton("Finish The Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }
            });
            alertq.show();
        }
        mQuizQuestion = questions[mQuestionIndex].getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        progressBar.incrementProgressBy(USER_PROGRESS);
        txtQuizStats.setText(UScore +"");
    }

    private void evaluateAnswer(boolean userGuess){
        boolean currentQuestionAnswer = questions[mQuestionIndex].ismAnswer();

        if(currentQuestionAnswer==userGuess){
                Toast.makeText(getApplicationContext(),R.string.correct_toast_message,Toast.LENGTH_SHORT).show();
                UScore=UScore+1;

        }
        else {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast_message,Toast.LENGTH_LONG).show();
        }
    }
}