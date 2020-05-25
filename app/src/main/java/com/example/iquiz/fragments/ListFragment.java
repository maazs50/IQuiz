package com.example.iquiz.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.example.iquiz.R;
import com.example.iquiz.adapters.QuizListAdpater;
import com.example.iquiz.models.QuizListModel;
import com.example.iquiz.view_models.QuizListViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements QuizListAdpater.OnQuizItemClicked {
    private RecyclerView listView;
    private ProgressBar listProgress;
    private QuizListViewModel viewModel;
    private QuizListAdpater adpater;
    private Animation fadeInAnim;
    private Animation fadeOutAnim;
    private NavController navController;
    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.list_view);
        listProgress = view.findViewById(R.id.list_progress);
        fadeInAnim = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        fadeOutAnim = AnimationUtils.loadAnimation(getContext(),R.anim.fade_out);
        navController = Navigation.findNavController(view);
        adpater = new QuizListAdpater(this);
        listView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        listView.setHasFixedSize(true);
        listView.setAdapter(adpater);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Initializing
        viewModel = new ViewModelProvider(getActivity()).get(QuizListViewModel.class);
        viewModel.getQuizListModelData().observe(getViewLifecycleOwner(), new Observer<List<QuizListModel>>() {
            @Override
            public void onChanged(List<QuizListModel> quizListModelList) {
                listView.startAnimation(fadeInAnim);
                listProgress.startAnimation(fadeOutAnim);
                adpater.setQuizListModels(quizListModelList);
                adpater.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        ListFragmentDirections.ActionListFragmentToDetailsFragment action = ListFragmentDirections.actionListFragmentToDetailsFragment();
        action.setPosition(position);
        navController.navigate(action);
    }
}
