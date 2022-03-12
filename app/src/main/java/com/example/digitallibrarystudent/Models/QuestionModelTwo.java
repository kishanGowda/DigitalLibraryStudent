package com.example.digitallibrarystudent.Models;

public class QuestionModelTwo {

    String infoText,file;

    public QuestionModelTwo( String infoText, String file) {

        this.infoText = infoText;
        this.file = file;
    }



    public String getInfoText() {
        return infoText;
    }

    public String getFile() {
        return file;
    }
}
