package com.example.digitallibrarystudent.Api;

import java.util.ArrayList;
import java.util.Date;

public class OverAllStateResponse {
    public boolean showLibrary;
    public ArrayList<TotalCount> totalCount;
    public int lastWeekLectureNotesCount;
    public int lastWeekVideoCount;
    public int lastWeekQuestionBankCount;
    public ArrayList<Subject> subjects;
    public ArrayList<QuestionBank> questionBank;
    public ArrayList<Video> video;
    public ArrayList<LectureNote> lectureNotes;

}


