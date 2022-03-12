package com.example.digitallibrarystudent.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
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

import com.example.digitallibrarystudent.Api.GetLibraryResponse;
import com.example.digitallibrarystudent.Fragment.PdfReader;
import com.example.digitallibrarystudent.Models.QuestionModelTwo;
import com.example.digitallibrarystudent.R;

import java.util.ArrayList;

public class QuestionAdapterTwo  extends RecyclerView.Adapter<QuestionAdapterTwo.MyviewHolder>{
    ArrayList<QuestionModelTwo> questionModels;
    Context context;
    private OnItemClickListener onItemClickListener;
    int chapterId,standardId,topicId;
    GetLibraryResponse getLibraryResponse;
    String pdfFile;

    public QuestionAdapterTwo(ArrayList<QuestionModelTwo> questionModels, Context context) {

    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_bank_item,parent,false);
        MyviewHolder cvh = new MyviewHolder(view,onItemClickListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, @SuppressLint("RecyclerView") int position) {
        QuestionModelTwo currentCards = this.questionModels.get(position);
        holder.content.setText(currentCards.getInfoText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String file=currentCards.getFile();
                    String name=currentCards.getInfoText();
                Fragment fragment = new PdfReader(file,name);
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionModels.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView content;
        ImageView edit;

        public MyviewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            content=itemView.findViewById(R.id.question_text_);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(listener!=null){
//                        int position=getAbsoluteAdapterPosition();
//                        if(position!=RecyclerView.NO_POSITION){
//                            listener.onItemClickListener(position);
//
//                        }
//
//                    }
//                }
//            });

        }
    }
    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener=  listener;
    }

}
