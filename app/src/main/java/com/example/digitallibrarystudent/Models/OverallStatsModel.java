package com.example.digitallibrarystudent.Models;

public class OverallStatsModel {
    int imageForCard;
    String zeroText, subjectText;

    public OverallStatsModel(int imageForCard, String zeroText, String subjectText) {
        this.imageForCard = imageForCard;
        this.zeroText = zeroText;
        this.subjectText = subjectText;

    }

    public int getImageForCard() {
        return imageForCard;
    }

    public String getZeroText() {
        return zeroText;
    }

    public String getSubjectText() {
        return subjectText;
    }


}



