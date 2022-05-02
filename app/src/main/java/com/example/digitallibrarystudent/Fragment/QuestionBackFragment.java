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

import com.example.digitallibrarystudent.Adapters.QuestionAdapterTwo;
import com.example.digitallibrarystudent.Api.ApiClient;
import com.example.digitallibrarystudent.Api.Content;
import com.example.digitallibrarystudent.Api.GetLibraryResponse;
import com.example.digitallibrarystudent.Api.LoginService;
import com.example.digitallibrarystudent.Models.QuestionModelTwo;
import com.example.digitallibrarystudent.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class QuestionBackFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    QuestionAdapterTwo adapter;
    ArrayList<QuestionModelTwo> questionModels;
    LinearLayoutManager layoutManager;
    //    int chapterId,topicID,standardId;
    LoginService loginService;
    Retrofit retrofit;
    LinearLayout noQuestion;
    String subjectName, standardName, topicName, chapterName, sectionName;
    int chapterId, topicId, standardId, subjectId;
    GetLibraryResponse getLibraryResponse;


    public QuestionBackFragment(int topicId, int chapterId) {
        this.topicId=topicId;
        this.chapterId=chapterId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question_back, container, false);
        apiInit();

        noQuestion = view.findViewById(R.id.no_question_avialbale);

//
//        chapterId = Integer.valueOf(getArguments().getString("chapterIdQ"));
//        topicID = Integer.valueOf(getArguments().getString("topicIdQ"));
//        standardId = Integer.valueOf(getArguments().getString("standardIdQ"));
//        Log.i("chapter2", String.valueOf(chapterId));
//        Log.i("topic2", String.valueOf(topicId));
//        Log.i("standard2", String.valueOf(standardId));
//        getLibrary();
        return view;

    }

    public void apiInit() {
        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();
    }


    public void getLibrary() {
        Call<GetLibraryResponse> call = loginService.getLibraryCall_notes(topicId, chapterId, "question-bank");
        call.enqueue(new Callback<GetLibraryResponse>() {
            @Override
            public void onResponse(Call<GetLibraryResponse> call, Response<GetLibraryResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                getLibraryResponse = response.body();
                ArrayList<Content> contents = getLibraryResponse.contents;
                int length = contents.size();


                Log.i("si", String.valueOf(length));
                if (length == 0) {
                    noQuestion.setVisibility(View.VISIBLE);
                } else {
                    questionModels = new ArrayList<>();
                    for (int i = 0; i <= length - 1; i++) {
                        questionModels.add(new QuestionModelTwo(getLibraryResponse.contents.get(i).title,getLibraryResponse.contents.get(i).file));

                    }
                    buildR();
                }
            }

            @Override
            public void onFailure(Call<GetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

            }
        });
    }


    //    public void forlecturer(){
//        questionModels=new ArrayList<>();
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//
//    }
    public void buildR() {
        recyclerView = view.findViewById(R.id.question_rvvv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuestionAdapterTwo(questionModels, getContext());
        recyclerView.setAdapter(adapter);

    }

}