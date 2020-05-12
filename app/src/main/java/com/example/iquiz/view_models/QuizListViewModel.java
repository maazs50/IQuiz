package com.example.iquiz.view_models;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iquiz.models.QuizListModel;
import com.example.iquiz.repo.FirebaseRepository;

import java.util.List;

public class QuizListViewModel extends ViewModel implements FirebaseRepository.OnFirestoreTaskComplete {
    private FirebaseRepository repository = new FirebaseRepository(this);
    private MutableLiveData<List<QuizListModel>> quizListModelData = new MutableLiveData<>();

    public QuizListViewModel(){
        //This is need to need inclucded in parameter constructer if we are using for multipurpose
        repository.getQuizData();
    }
    @Override
    public void quizListDataAdded(List<QuizListModel> quizListModelList) {
        quizListModelData.setValue(quizListModelList);
    }

    @Override
    public void onError(Exception e) {

    }

    public LiveData<List<QuizListModel>> getQuizListModelData() {
        return quizListModelData;
    }

}
