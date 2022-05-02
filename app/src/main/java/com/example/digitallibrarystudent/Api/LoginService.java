package com.example.digitallibrarystudent.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {

    String token="Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTI0NiwicGhvbmUiOiIrOTE5MzkxODE2NTY1IiwidXJsIjoidGVzdC50aGVjbGFzc3Jvb20uYml6Iiwib3JnSWQiOiI0Y2IyNTA5ZC03MGY1LTQzNWUtODc5Mi1kMjQ5Mzc3NDNiNTMiLCJicm93c2VyTG9naW5Db2RlIjoiKzkxOTM5MTgxNjU2NTEyNDYzMWRiNmQ3OC0wNzJhLTQ4MWMtYjQ5Zi1iMzRjMWRmY2I2MGEiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY1MTQ3NDYzNn0.6yHqTwK_3n5paRxpH9NUNPo1ooXNdv8440pSupiNEcc";
    String link="orgurl:test.theclassroom.biz";

//home page student
    @Headers({token,link})
    @GET("admin-library/standardById")
     Call<OverAllStateResponse> overallCall();

    //GETCHAPTERLIST
    @Headers({token,link})
    @GET(" admin-library/chapter-list")
    Call<GetChapterResponse> getChapterCall(@Query("subjectId") int subjectId);

    //get topic
    @Headers({token,link})
    @GET(" admin-library/topic-list-by-chapterId")
    Call<List<GetTopicsResponse>> getTopicsCall(@Query("chapterId") int chapterId, @Query("subjectId") int subjectId);



    //library

    @Headers({token,link})
    @GET(" admin-library/library-contents")
    Call<GetLibraryResponse> getLibraryCall(@Query("topicId") int topicId, @Query("chapterId") int chapterId);

    //getLibray_notes
    @Headers({token,link})
    @GET("admin-library/library-contents")
    Call<GetLibraryResponse> getLibraryCall_notes(@Query("topicId")int topicId,@Query("chapterId")int chapterId,@Query("type")String type);


}
