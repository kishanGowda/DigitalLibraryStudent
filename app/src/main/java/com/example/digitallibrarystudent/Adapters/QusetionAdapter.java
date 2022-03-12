package com.example.digitallibrarystudent.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibrarystudent.Fragment.PdfReader;
import com.example.digitallibrarystudent.Models.QuestionModel;
import com.example.digitallibrarystudent.Models.VideoModel;
import com.example.digitallibrarystudent.R;

import java.util.ArrayList;

public class QusetionAdapter  extends RecyclerView.Adapter<QusetionAdapter.ViewHolder> {
        ArrayList<QuestionModel> lecturerModels;
        Context activity;
public QusetionAdapter(ArrayList<QuestionModel> lecturerModels, Context activity) {
        this.lecturerModels=lecturerModels;
        this.activity=activity;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecturer_item ,parent,false);
        ViewHolder cvh = new ViewHolder(view);
        return cvh;
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionModel currentCards = this.lecturerModels.get(position);
        holder.icon.setImageResource(currentCards.getIcon());
        holder.chapterName.setText(currentCards.getChapterName());
        holder.topicName.setText(currentCards.getTopicName());
        holder.subjectName.setText(currentCards.getSubjectName());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String file=  currentCards.getFile();
            Log.i("file", file);
            String name=currentCards.getTopicName();
            Fragment fragment = new PdfReader(file,name);
            FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    });

}

@Override
public int getItemCount() {
        return lecturerModels.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView icon;
    TextView subjectName,chapterName,topicName;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        icon=itemView.findViewById(R.id.imageView_icon);
        topicName=itemView.findViewById(R.id.topic_name_lec);
        chapterName=itemView.findViewById(R.id.chapter_lec);
        subjectName=itemView.findViewById(R.id.subject_lec);
    }
}
}
