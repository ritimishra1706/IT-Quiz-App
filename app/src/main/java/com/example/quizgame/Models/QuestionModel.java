package com.example.quizgame.Models;

public class QuestionModel {
    private String question,optionA,optionB,optionC,optionD,correctAns;
    public QuestionModel(String question,String optionA, String optionB,String optionC,String optionD,String correctAns){
        this.question=question;
        this.optionA=optionA;
        this.optionB=optionB;
        this.optionC=optionC;
        this.optionD=optionD;
        this.correctAns=correctAns;


    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAns() {
        return correctAns;
    }
     public String getOptionA() {
        return correctAns;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }


}
