package com.example.iquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {
    public static final String TAG = StartFragment.class.getSimpleName();
    private ProgressBar startProgress;
    private TextView startFeedback;
    private FirebaseAuth mAuth;
    private NavController navController;
    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startProgress = view.findViewById(R.id.start_progress);
        startFeedback = view.findViewById(R.id.start_feedback);
        mAuth = FirebaseAuth.getInstance();
        navController = Navigation.findNavController(view);
        startFeedback.setText("Checking user....");
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            startFeedback.setText("Account created....");
            mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        navController.navigate(R.id.action_startFragment_to_listFragment);
                    }else{
                        Log.d(TAG,"Start error "+task.getException().toString());
                    }
                }
            });
        } else {
            startFeedback.setText("Logged in....");
                navController.navigate(R.id.action_startFragment_to_listFragment);
        }
    }
}
