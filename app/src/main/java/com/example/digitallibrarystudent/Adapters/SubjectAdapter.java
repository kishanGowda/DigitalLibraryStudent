package com.example.digitallibrarystudent.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibrarystudent.Fragment.ChapterFragment;
import com.example.digitallibrarystudent.Models.SubjectModel;
import com.example.digitallibrarystudent.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder> implements Filterable {

    List<SubjectModel> subjectModels;
    Context context;
    NavController navController;
    List<SubjectModel> subjectModelsFull;
    private OnItemClickListener onItemClickListener;


    public SubjectAdapter(List<SubjectModel> subject, Context context) {
        this.subjectModels = subject;
        this.context = context;
        this.subjectModelsFull = new ArrayList<>(subjectModels);


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjects_layout, parent, false);
        MyViewHolder cvh = new MyViewHolder(view,onItemClickListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SubjectModel currentCards = subjectModels.get(position);

        holder.subjectName.setText(currentCards.getSubjectName());
        holder.chapters.setText(currentCards.getChapters());
        holder.ntsCount.setText(String.valueOf(currentCards.getNtsCount()));
        holder.vdoCount.setText(String.valueOf(currentCards.getVdoCount()));
        holder.quesCount.setText(String.valueOf(currentCards.getQuesCount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=currentCards.getSubjectId();
                String name=currentCards.getSubjectName();
                Fragment fragment = new ChapterFragment(id,name);
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
        return subjectModels.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectName, chapters, ntsCount, vdoCount, quesCount;

        public MyViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subject_textview);
            chapters = itemView.findViewById(R.id.chapters_tv);
            ntsCount = itemView.findViewById(R.id.notes_count_tv);
            vdoCount = itemView.findViewById(R.id.video_count_tv);
            quesCount = itemView.findViewById(R.id.question_count_tv);


        }
    }
    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener=  listener;
    }
    @Override
    public Filter getFilter() {

return filter;

       }
       private Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<SubjectModel> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(subjectModelsFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (SubjectModel item : subjectModelsFull) {
                        if (item.getSubjectName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }


           @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                subjectModels.clear();
                subjectModels.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };


}
