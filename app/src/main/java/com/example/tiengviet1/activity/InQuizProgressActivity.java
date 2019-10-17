package com.example.tiengviet1.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiengviet1.R;
import com.example.tiengviet1.dto.QuizDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InQuizProgressActivity extends AppCompatActivity {


    ArrayList<QuizDTO> list = new ArrayList<>();
    ArrayList<QuizDTO> temp = new ArrayList<>();
    private Button btn1,btn2,btn3,btn4;
    private String rightAnswer;
    private String[] arrQ;
    private TextView txtMark, txtQuestion;
    private ImageView imgQuiz;
    private String topic;
    int markCount = 0;
    int quizCount = 1;
    private static final int QUIZ_COUNT = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_quiz_progress);
        setupView();
        Intent intent = this.getIntent();
        list = (ArrayList<QuizDTO>) intent.getSerializableExtra("listQuizzes");
        System.out.println("List: "+list.size());
        topic = intent.getStringExtra("topic");
        System.out.println("Topic: " + topic);

//        txtMark.setText(topic);

        for(int i =0;i<list.size();i++){

            if(list.get(i).getQuizType().equals(topic)){
                temp.add(list.get(i));
            }
        }
        showNextQuestion();


    }


    public void setupView(){
        btn1 = findViewById(R.id.imageView9);
        btn2 = findViewById(R.id.imageView10);
        btn3 = findViewById(R.id.imageView11);
        btn4 = findViewById(R.id.imageView12);
        txtMark = findViewById(R.id.txtMark);
        txtQuestion = findViewById(R.id.txtQuestion);
        imgQuiz = findViewById(R.id.imgQuiz);
    }

    public void showNextQuestion(){
        Random random = new Random();
        int randumNumber = random.nextInt(temp.size());
        arrQ = temp.get(randumNumber).getArrAnswer().split(";");
        btn1.setText(arrQ[0]);
        btn2.setText(arrQ[1]);
        btn3.setText(arrQ[2]);
        btn4.setText(arrQ[3]);
        rightAnswer = temp.get(randumNumber).getRightAnswer();
        Glide.with(this).load(temp.get(randumNumber).getImg().getImgPath()).into(imgQuiz);
        txtQuestion.setText(temp.get(randumNumber).getQuestion());

        temp.remove(randumNumber);
    }

    public void checkAnswer(View view){

        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;
        if(btnText.equals(rightAnswer)){

            alertTitle = "Chính xác!";
            markCount++;
            txtMark.setText(markCount+"/10 Điểm");
        }else{

            alertTitle = "Sai rồi";

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Kết Quả: " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(quizCount == QUIZ_COUNT){
                    Intent intent = new Intent(InQuizProgressActivity.this, ResultTestActivity.class);
                    intent.putExtra("finalMark", markCount);
                    startActivity(intent);
                }else{
                    quizCount++;
                    showNextQuestion();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void clickToExit(View view) {
        this.onBackPressed();
    }
}
