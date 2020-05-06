package com.example.iquiz.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuizListAdpater extends RecyclerView.Adapter<QuizListAdpater.QuizViewHolder> {
    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
