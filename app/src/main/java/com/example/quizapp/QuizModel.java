package com.example.quizapp;

import java.util.ArrayList;

public class QuizModel {
    String name;
    ArrayList<String> sublist;
    int selectedposition;
    String answer;

    public boolean isIschecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    boolean ischecked;

    public int getSelectedposition() {
        return selectedposition;
    }

    public void setSelectedposition(int selectedposition) {
        this.selectedposition = selectedposition;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSublist() {
        return sublist;
    }

    public void setSublist(ArrayList<String> sublist) {
        this.sublist = sublist;
    }

    public QuizModel(String name, ArrayList<String> sublist){
        this.name = name;
        this.sublist = sublist;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", answer=" + answer +
                ", sublist=" + sublist +
                '}';
    }
}
