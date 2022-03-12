package com.example.digitallibrarystudent.Api;


import java.util.ArrayList;

public class GetChapterResponse {
    public int chapterCount;
    public ArrayList<Chapter2> chapters;

    public GetChapterResponse(int chapterCount, ArrayList<Chapter2> chapters) {
        this.chapterCount = chapterCount;
        this.chapters = chapters;
    }

    public int getChapterCount() {
        return chapterCount;
    }

    public ArrayList<Chapter2> getChapters() {
        return chapters;
    }
}

