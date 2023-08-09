package com.example.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizgame.Models.QuestionModel;
import com.example.quizgame.databinding.ActivityQuestionsBinding;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    ArrayList<QuestionModel> list = new ArrayList<>();
    ActivityQuestionsBinding binding;
    private int count=0;
    private int position=0;
    private int score=0;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        resetTimer();
        timer.start();
        String setName=getIntent().getStringExtra("set");
        if(setName.equals("SET-1")){
            setOne();
        }
        else if(setName.equals("SET-2")){
            setTwo();
        }
        for(int i=0;i<4;i++){
            binding.optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer((Button) view);
                }
            });
        }
        playAnimation(binding.question,0,list.get(position).getQuestion());
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timer!=null){
                    timer.cancel();
                }
                timer.start();
                binding.buttonSubmit.setEnabled(false);
                binding.buttonSubmit.setAlpha((float)0.3);
                enableOption(true);
                position++;
                if(position==list.size()){
                    Intent intent=new Intent(QuestionsActivity.this,ScoreActivity.class);
                    intent.putExtra("score",score);
                    intent.putExtra("total",list.size());
                    startActivity(intent);
                    finish();
                    return;
                }
                count=0;
                playAnimation(binding.question,0,list.get(position).getQuestion());
            }
        });
    }

    private void resetTimer() {
        timer= new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.timer.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(QuestionsActivity.this);
                dialog.setContentView(R.layout.timecounter);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setCancelable(false);

                dialog.findViewById(R.id.tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(QuestionsActivity.this,SetsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                dialog.show();

            }
        };
    }

    private void playAnimation(View view,int value, String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {
                        if(value==0 && count<4){
                            String option = "";
                            if(count==0){
                                option=list.get(position).getOptionA();
                            } else if (count==1) {
                                option=list.get(position).getOptionB();
                            } else if (count==2) {
                                option=list.get(position).getOptionC();

                            } else if (count==3 ){
                                option=list.get(position).getOptionD();

                            }
                            playAnimation(binding.optionContainer.getChildAt(count),0,option);
                            count++;
                        }

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {
                        if(value==0){
                            try{
                                ((TextView)view).setText(data);
                                binding.totalQuestions.setText(position+1+"/"+list.size());

                            } catch(Exception e){
                                ((Button)view).setText(data);
                            }
                            view.setTag(data);
                            playAnimation(view,1,data);


                        }

                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {

                    }
                });
    }
    private void enableOption(boolean enable){
        for(int i=0;i<4;i++) {
            binding.optionContainer.getChildAt(i).setEnabled(enable);

            if (enable) {
                binding.optionContainer.getChildAt(i).setBackgroundResource(R.drawable.button_bg);
            }
        }
    }
    private void checkAnswer(Button selectedOption){
        if(timer!=null){
            timer.cancel();
        }
        binding.buttonSubmit.setEnabled(true);
        binding.buttonSubmit.setAlpha(1);
        if(selectedOption.getText().toString().equals(list.get(position).getCorrectAns())){
            score++;
            selectedOption.setBackgroundResource(R.drawable.correct_ans);

        }
        else{
            selectedOption.setBackgroundResource(R.drawable.wrong_ans);
            Button correctOption =(Button) binding.optionContainer.findViewWithTag(list.get(position).getCorrectAns());
            correctOption.setBackgroundResource(R.drawable.correct_ans);
        }


    }

    private void setTwo() {
        list.add(new QuestionModel("Given 0.006, 1.2, and 6/25, what is the fourth proportional?","48","4","36","3","48"));
        list.add(new QuestionModel("A retailer paid Rs P for 25 identical toys and then sold some of them for Rs P. How many did he sell if he estimated his profit at 8% and used the selling price as the base instead of the cost price?","20","21","23","24","23"));
        list.add(new QuestionModel("On January 1, 2016, Rs.12500 was invested at a rate of 4% simple interest p.a. On July 1, 2016, how much interest in rupees will have accrued at the end of the day?","240","250","400","500","250"));
        list.add(new QuestionModel("What is the difference between m and n if the HCF of 180 and 432 is (180m + 432n), where m and n are integers?","3","7","8","9","7"));

    }

    private void setOne() {

        list.add(new QuestionModel("Given 0.006, 1.2, and 6/25, what is the fourth proportional?","48","4","36","3","48"));
        list.add(new QuestionModel("A retailer paid Rs P for 25 identical toys and then sold some of them for Rs P. How many did he sell if he estimated his profit at 8% and used the selling price as the base instead of the cost price?","20","21","23","24","23"));
        list.add(new QuestionModel("On January 1, 2016, Rs.12500 was invested at a rate of 4% simple interest p.a. On July 1, 2016, how much interest in rupees will have accrued at the end of the day?","240","250","400","500","250"));
        list.add(new QuestionModel("What is the difference between m and n if the HCF of 180 and 432 is (180m + 432n), where m and n are integers?","3","7","8","9","7"));

    }
}