package com.example.digitallibrarystudent.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.digitallibrarystudent.Adapters.LecturerAdapter;
import com.example.digitallibrarystudent.Adapters.QusetionAdapter;
import com.example.digitallibrarystudent.Adapters.SubjectAdapter;
import com.example.digitallibrarystudent.Adapters.VideoAdapter;
import com.example.digitallibrarystudent.Api.ApiClient;
import com.example.digitallibrarystudent.Api.LoginService;
import com.example.digitallibrarystudent.Api.OverAllStateResponse;
import com.example.digitallibrarystudent.Api.TotalCount;
import com.example.digitallibrarystudent.Models.LecturerModel;
import com.example.digitallibrarystudent.Models.OverallStatsModel;
import com.example.digitallibrarystudent.Models.QuestionModel;
import com.example.digitallibrarystudent.Models.SubjectModel;
import com.example.digitallibrarystudent.Models.VideoModel;
import com.example.digitallibrarystudent.R;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainFragment extends Fragment {

    View view;
    Retrofit retrofit;
    int length=0;
    LoginService loginService;
    VideoAdapter videoAdapter;
    SubjectAdapter subjectAdapter;
    RecyclerView recyclerView, recyclerViewVideo,recyclerViewQuestion,recyclerViewSubject;
    LinearLayoutManager layoutManager, layoutManagerVideo,layoutManagerQuestion,layoutManagerSubject;
    ArrayList<LecturerModel> lecturerModels;
    OverAllStateResponse overAllStateResponse;
    LinearLayout foeLecturer,forVideo,forQuestion;
    LecturerAdapter lecturerAdapter;
    QusetionAdapter qusetionAdapter;
    ArrayList<VideoModel> videoModels;
    ArrayList<QuestionModel>questionModels;
    ArrayList<SubjectModel>subjectModels;



    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        foeLecturer=view.findViewById(R.id.lect_linear);
        forVideo=view.findViewById(R.id.video_linear);
        forQuestion=view.findViewById(R.id.question_linear);
        apiInit();
        getStudHomeP();
        getSubject();

        return view;
    }
    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }
    public void getStudHomeP()
    {
        Call<OverAllStateResponse> call=loginService.overallCall();
        call.enqueue(new Callback<OverAllStateResponse>() {
            @Override
            public void onResponse(Call<OverAllStateResponse> call, Response<OverAllStateResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                overAllStateResponse=response.body();
                List<TotalCount> totalCount=overAllStateResponse.totalCount;

                int lecSize=overAllStateResponse.lectureNotes.size();
                length=overAllStateResponse.subjects.size();
                Log.i("length", String.valueOf(length));
                if(length==0){
                }
                else{
                    getSubject();
                }
                Log.i("size", String.valueOf(lecSize));
                if(lecSize==0){
                    foeLecturer.setVisibility(View.GONE);
                }
                else {
                    lecturerModels=new ArrayList<>();
                    for (int i = 0; i <= overAllStateResponse.lectureNotes.size() - 1; i++) {
                        lecturerModels.add(new LecturerModel(R.drawable.sbj_chemistry,overAllStateResponse.lectureNotes.get(i).topic.name,overAllStateResponse.lectureNotes.get(i).chapter.name,overAllStateResponse.lectureNotes.get(i).subject.name,overAllStateResponse.lectureNotes.get(i).file));
                    }
                    build();


                }
                if(overAllStateResponse.video.size()==0) {
                 forVideo.setVisibility(View.GONE);
                }else{
                    videoModels=new ArrayList<>();
                    for (int j=0;j<=overAllStateResponse.video.size()-1;j++){

                        videoModels.add(new VideoModel(R.drawable.mapchem,overAllStateResponse.video.get(j).topic.name,overAllStateResponse.video.get(j).chapter.name,overAllStateResponse.video.get(j).subject.name,overAllStateResponse.video.get(j).link,overAllStateResponse.video.get(j).title,overAllStateResponse.video.get(j).status,overAllStateResponse.video.get(j).file));
                    }
                    buildVideo();
                }
                if(overAllStateResponse.questionBank.size()==0) {
                    forQuestion.setVisibility(View.GONE);
                }else{
                    questionModels=new ArrayList<>();
                    for (int j=0;j<=overAllStateResponse.questionBank.size()-1;j++){
                        questionModels.add(new QuestionModel(R.drawable.mapchem,overAllStateResponse.questionBank.get(j).topic.name,overAllStateResponse.questionBank.get(j).chapter.name,overAllStateResponse.questionBank.get(j).subject.name,overAllStateResponse.questionBank.get(j).file));
                    }
                    buildQuestion();
                }

            }
            @Override
            public void onFailure(Call<OverAllStateResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error fail in home page", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getSubject()
    {
        Call<OverAllStateResponse> call=loginService.overallCall();
        call.enqueue(new Callback<OverAllStateResponse>() {
            @Override
            public void onResponse(Call<OverAllStateResponse> call, Response<OverAllStateResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
               OverAllStateResponse response1=response.body();
                subjectModels=new ArrayList<>();

                int length=0;
                      length=  response1.subjects.size();
                Log.i("hh", String.valueOf(length));
                for(int i=0;i<=length-1;i++){
                    subjectModels.add(new SubjectModel(response1.subjects.get(i).subjects_name,String.valueOf(response1.subjects.get(i).chapterCount),Integer.valueOf(response1.subjects.get(i).notesCount),
                           Integer.valueOf( response1.subjects.get(i).videoCount),Integer.valueOf(response1.subjects.get(i).quesBankCount),response1.subjects.get(i).subjects_id,R.drawable.sbj_chemistry));
                }
                buildSubject();


            }
            @Override
            public void onFailure(Call<OverAllStateResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error fail in home page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void build(){
            recyclerView = view.findViewById(R.id.for_ln_rv);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            lecturerAdapter = new LecturerAdapter(lecturerModels,getActivity());
            recyclerView.setAdapter(lecturerAdapter);
            recyclerView.setNestedScrollingEnabled(false);
        }
    public void buildVideo(){
        recyclerViewVideo=view.findViewById(R.id.for_v_rv);
        recyclerViewVideo.setHasFixedSize(true);
        layoutManagerVideo = new GridLayoutManager(getContext(),2);
        recyclerViewVideo.setLayoutManager(layoutManagerVideo);
        videoAdapter = new VideoAdapter(videoModels,getContext());
        recyclerViewVideo.setAdapter(videoAdapter);
        recyclerViewVideo.setNestedScrollingEnabled(false);
    }


    public void buildQuestion(){
        recyclerViewQuestion=view.findViewById(R.id.for_q_rv);
        recyclerViewQuestion.setHasFixedSize(true);
        layoutManagerQuestion = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewQuestion.setLayoutManager(layoutManagerQuestion);
         qusetionAdapter= new QusetionAdapter(questionModels,getContext());
        recyclerViewQuestion.setAdapter(qusetionAdapter);
        recyclerViewQuestion.setNestedScrollingEnabled(false);
    }
    public void buildSubject() {
        recyclerViewSubject = view.findViewById(R.id.subject_rv);
        recyclerViewSubject.setHasFixedSize(true);
        layoutManagerSubject = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerViewSubject.setLayoutManager(layoutManagerSubject);
        subjectAdapter = new SubjectAdapter(subjectModels, getContext());
        recyclerViewSubject.setAdapter(subjectAdapter);
        recyclerViewSubject.setNestedScrollingEnabled(false);
    }
}


