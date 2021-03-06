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

import com.example.digitallibrarystudent.Adapters.TopicModelAdapter;
import com.example.digitallibrarystudent.Api.ApiClient;
import com.example.digitallibrarystudent.Api.GetTopicsResponse;
import com.example.digitallibrarystudent.Api.LoginService;
import com.example.digitallibrarystudent.Models.TopicModel;
import com.example.digitallibrarystudent.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TopicFragment extends Fragment {
View view;
int subjectId,chapterId;
String topic;
TextView topicName;
Retrofit retrofit;
LoginService loginService;
ImageView back;
ArrayList<TopicModel> topicModels;
TopicModelAdapter adapter;
RecyclerView recyclerView;
LinearLayout linearLayout;
LinearLayoutManager linearLayoutManager;



    public TopicFragment(int subjectId, int chapterId, String topic) {
        // Required empty public constructor
        this.subjectId=subjectId;
        this.chapterId=chapterId;
        this.topic=topic;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_topic, container, false);
        back=view.findViewById(R.id.back_no_topic);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        topicName=view.findViewById(R.id.topic_chapter_name);
        topicName.setText(topic);
        Log.i("s", String.valueOf(subjectId));
        Log.i("c", String.valueOf(chapterId));

        apiInit();
        getTopics();
        return view;
    }
    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }

    public void getTopics()
    {
        Call<List<GetTopicsResponse>> call=loginService.getTopicsCall(chapterId,subjectId);
        call.enqueue(new Callback<List<GetTopicsResponse>>() {
            @Override
            public void onResponse(Call<List<GetTopicsResponse>> call, Response<List<GetTopicsResponse>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
            List <GetTopicsResponse>   getTopicsResponse=  response.body();
                        topicModels=new ArrayList<>();
                       int topicSize= getTopicsResponse.size();
                Log.i("topicSize", String.valueOf(topicSize));
                if (topicSize==0){
                    linearLayout = view.findViewById(R.id.no_topic2_avialbale_);
                    linearLayout.setVisibility(View.VISIBLE);

                }
                else {

                    for (int i = 0; i <= topicSize - 1; i++) {


                        topicModels.add(new TopicModel(Integer.valueOf(getTopicsResponse.get(i).getVideoCount()), Integer.valueOf(getTopicsResponse.get(i).getQuesBankCount()), Integer.valueOf(getTopicsResponse.get(i).notesCount), String.valueOf(getTopicsResponse.get(i).topicName), String.valueOf(getTopicsResponse.get(i).getTopicId())));


                    }
                    buildRecyclerView();
                }
        }

            @Override
            public void onFailure(Call<List<GetTopicsResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Error in get topic", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void buildRecyclerView(){
        recyclerView=view.findViewById(R.id.idRVCourses);
        adapter = new TopicModelAdapter(topicModels,getContext(),subjectId,chapterId);
        LinearLayoutManager manager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}