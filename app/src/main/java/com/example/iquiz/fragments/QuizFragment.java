package com.example.iquiz.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.iquiz.R;
import com.example.iquiz.models.QuestionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {
    private FirebaseFirestore firebaseFirestore;
    private String quizId;
    private TextView quizTitle;
    private long totalQuestionsToAnswer;
    private List<QuestionModel> allQuestionsList= new ArrayList<>();
    private List<QuestionModel> questionsToAnserList = new ArrayList<>();
    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        quizTitle = view.findViewById(R.id.quiz_title);

        quizId = QuizFragmentArgs.fromBundle(getArguments()).getQuizId();
        totalQuestionsToAnswer = QuizFragmentArgs.fromBundle(getArguments()).getTotalQuestions();
        firebaseFirestore.collection("QuizList").
                document(quizId).collection("Questions").
                get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    allQuestionsList = task.getResult().toObjects(QuestionModel.class);
                    Log.d("Tag","Question list : "+allQuestionsList.get(0).getQuestion());
                    pickQuestions();
                }else{
                    quizTitle.setText("Error loading data");
                }
            }
        });
    }

    private void pickQuestions() {
        for (int i=0; i < totalQuestionsToAnswer; i++){
            int randomNumber = getRandomInteger(allQuestionsList.size(),0);
            questionsToAnserList.add(allQuestionsList.get(randomNumber));
            allQuestionsList.remove(randomNumber);
            Log.d("Tag",questionsToAnserList.get(i).getQuestion());
        }
    }

    private static int getRandomInteger(int size, int i) {
        return ((int) (Math.random()*(size - i)))+i;
    }
}
