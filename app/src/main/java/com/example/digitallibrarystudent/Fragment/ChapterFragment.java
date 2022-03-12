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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitallibrarystudent.Adapters.ChapterDetailsAdapter;
import com.example.digitallibrarystudent.Api.ApiClient;
import com.example.digitallibrarystudent.Api.Chapter;
import com.example.digitallibrarystudent.Api.GetChapterResponse;
import com.example.digitallibrarystudent.Api.LoginService;
import com.example.digitallibrarystudent.Models.ChapterDetails;
import com.example.digitallibrarystudent.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ChapterFragment extends Fragment {
View view;
int id;
Retrofit retrofit;
RecyclerView recyclerView;
LinearLayoutManager linearLayoutManager;
LoginService loginService;
GetChapterResponse getChapterListResponse;
ChapterDetailsAdapter chapterDetailsAdapter;
LinearLayout linearLayout;
ArrayList<ChapterDetails> chapters;
String subjectName;
TextView textView;
ImageView back;


    public ChapterFragment(int id, String name) {
        this.id=id;
        subjectName=name;
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_chapter, container, false);
        textView=view.findViewById(R.id.subject_name);
        textView.setText(subjectName);
        back=view.findViewById(R.id.back_chapter_detials);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        apiInIt();
        getChapter();
        linearLayout=view.findViewById(R.id.no_chapter_avialbale_);

        return  view;
    }
    public void apiInIt()
    {
        retrofit= ApiClient.getRetrofit();
        loginService=ApiClient.getApiService();
    }

    public void getChapter()
    {
        Call<GetChapterResponse> call=loginService.getChapterCall(id);
        call.enqueue(new Callback<GetChapterResponse>() {
            @Override
            public void onResponse(Call<GetChapterResponse> call, Response<GetChapterResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                getChapterListResponse=response.body();

                int count=getChapterListResponse.chapterCount;
                Log.i("count", String.valueOf(count));
                if(count==0){
                    linearLayout.setVisibility(View.VISIBLE);
                }else{
                    chapters=new ArrayList<>();
                    for (int i=0;i<=count-1;i++){
                        chapters.add(new ChapterDetails(String.valueOf(getChapterListResponse.chapters.get(i).chapterNo),getChapterListResponse.chapters.get(i).topicCount,getChapterListResponse.chapters.get(i).chapterName,
                                Integer.valueOf(getChapterListResponse.chapters.get(i).videoCount),Integer.valueOf(getChapterListResponse.chapters.get(i).notesCount),
                                Integer.valueOf(getChapterListResponse.chapters.get(i).quesBankCount),getChapterListResponse.chapters.get(i).chapterId));
                    }
                    buildRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<GetChapterResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error in get chapter", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void buildRecyclerView() {
        recyclerView = view.findViewById(R.id.subject_rvv);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager= new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        chapterDetailsAdapter = new ChapterDetailsAdapter(chapters, getContext(),id);
        recyclerView.setAdapter(chapterDetailsAdapter);
    }
}