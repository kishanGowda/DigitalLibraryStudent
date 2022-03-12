package com.example.digitallibrarystudent.Api;

public class GetTopicsResponse {
    public int topicId;
        public String topicName;
        public String notesCount;
        public String videoCount;
        public String quesBankCount;

    public GetTopicsResponse(int topicId, String topicName, String notesCount, String videoCount, String quesBankCount) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.notesCount = notesCount;
        this.videoCount = videoCount;
        this.quesBankCount = quesBankCount;
    }

    public int getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getNotesCount() {
        return notesCount;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public String getQuesBankCount() {
        return quesBankCount;
    }
}
