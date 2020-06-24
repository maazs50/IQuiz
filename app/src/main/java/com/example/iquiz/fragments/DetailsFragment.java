package com.example.iquiz.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iquiz.R;
import com.example.iquiz.models.QuizListModel;
import com.example.iquiz.view_models.QuizListViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements View.OnClickListener {
    private NavController navController;
    private QuizListViewModel viewModel;
    private int position;
    private ImageView iv_details;
    private TextView tv_difficulty, tv_question, tv_desc, tv_title;
    private Button btn_start;
    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        position = DetailsFragmentArgs.fromBundle(getArguments()).getPosition();
        Log.i("APP_LOG","position : "+position);
        iv_details = view.findViewById(R.id.details_image);
        tv_title = view.findViewById(R.id.details_title);
        tv_difficulty = view.findViewById(R.id.details_difficulty_text);
        tv_question = view.findViewById(R.id.details_questions_text);
        tv_desc = view.findViewById(R.id.details_desc);
        btn_start = view.findViewById(R.id.details_start_btn);
        btn_start.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(QuizListViewModel.class);
        viewModel.getQuizListModelData().observe(getViewLifecycleOwner(), new Observer<List<QuizListModel>>() {
            @Override
            public void onChanged(List<QuizListModel> quizListModelList) {
                QuizListModel model = quizListModelList.get(position);
                Glide.with(getContext()).load(model.getImage()).centerCrop().placeholder(R.drawable.placeholder_image).into(iv_details);
                tv_title.setText(model.getName());
                tv_difficulty.setText(model.getLevel());
                tv_question.setText(model.getQuestions()+"");
                tv_desc.setText(model.getDesc());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_start_btn:
                DetailsFragmentDirections.ActionDetailsFragmentToQuizFragment action = DetailsFragmentDirections.actionDetailsFragmentToQuizFragment();
                action.setPosition(position);
                navController.navigate(action);
                break;
        }
    }
}
