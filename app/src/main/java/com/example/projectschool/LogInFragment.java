package com.example.projectschool;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


//public class LogInFragment extends Fragment {



    //public LogInFragment() {
        // Required empty public constructor
    //}



    //@Override
   // public View onCreateView(LayoutInflater inflater, ViewGroup container,
           //                  Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_log_in, container, false);
    //}
    //@Override
    //public void onViewCreated(View view, Bundle savedInstanceState) {
      //  super.onViewCreated(view, savedInstanceState);
        //TextView moveToSignUp = view.findViewById(R.id.moveToSignUp);
        //moveToSignUp.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
             //   WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();
           //     welcomeActivity.changeFragmentToSignUp();
         //   }
       // });
   // }

//}
public class LogInFragment extends Fragment {

    public interface LogInListener {
        void changeFragmentToSignUp();
    }

    LogInListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getActivity() instanceof LogInListener) {
            listener = (LogInListener) getActivity();
        } else {
            throw new RuntimeException(getActivity().toString()
                    + " must implement LogInListener");
        }
//        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView moveToSignUp = view.findViewById(R.id.moveToSignUp);
        EditText password= view.findViewById(R.id.loginPassword);
        EditText userName= view.findViewById(R.id.loginUserName);
        Button logClick= view.findViewById(R.id.logClick);


        moveToSignUp.setOnClickListener(new View.OnClickListener() {
          @Override
        public void onClick(View v) {
           WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();
             welcomeActivity.changeFragmentToSignUp();
           }
         });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
