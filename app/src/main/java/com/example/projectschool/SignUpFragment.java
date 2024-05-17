package com.example.projectschool;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class SignUpFragment extends Fragment {
    public interface SignUpListener {
        void changeFragmentToLogIn();
    }
    SignUpListener listener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getActivity() instanceof LogInFragment.LogInListener) {
            listener = (SignUpFragment.SignUpListener) getActivity();
        }else {
                throw new RuntimeException(getActivity().toString()
                        + " must implement SignUpListener");
            }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView moveToLogIn = view.findViewById(R.id.moveToLoIn);
        EditText password= view.findViewById(R.id.signUpPassword);
        EditText userName= view.findViewById(R.id.signUpUserName);


        moveToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();
                welcomeActivity.changeFragmentToLogIn();
            }
        });
    }
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


}