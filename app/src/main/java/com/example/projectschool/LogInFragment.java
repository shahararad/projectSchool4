package com.example.projectschool;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.RoomDatabase;
import com.example.projectschool.UserDAO;
import com.example.projectschool.DB.AppDatabase;
import java.util.concurrent.CountDownLatch;

import java.util.List;

public class LogInFragment extends Fragment {
    private static AppDatabase appDatabase;
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

        logClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userName.getText().toString().trim();
                String pass = password.getText().toString().trim();
                // בדיקת תקינות שם משתמש וסיסמה
                if (username.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getActivity(), "אנא מלא את כל השדות", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.length() < 8) {
                    Toast.makeText(getActivity(), "הסיסמה לא נכונה(חייבת להיות 8 תווים לפחות)", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pass.matches(".*\\d.*") || !pass.matches(".*[a-z].*") || !pass.matches(".*[A-Z].*")) {
                    Toast.makeText(getActivity(), "הסיסמה חייבת לכלול לפחות אות אחת גדולה, אות אחת קטנה ומספר אחד", Toast.LENGTH_SHORT).show();
                    return;
                }



                if(lastCheack(username,pass )){
                    System.out.println("הי עברת את הבדיקה של המשתמש מיד נעביר אקטיביטי");
                    Intent intent = new Intent(getActivity(), HomePageActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }





            }
        });



        moveToSignUp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();
             welcomeActivity.changeFragmentToSignUp();
          }
        });
    }


    private boolean lastCheack (String username,String pass){
        LiveData<List<User>> allUsers= appDatabase.userDAO().getAllUsers();
        User user = appDatabase.userDAO().getUser(username,pass);
        if (user!=null){
            return true;
        }
        else {
            System.out.println("לא עברת את בדיקת הההתחברות שלי אתה לא קיים לי אצלי!!!!!!!");
            return false;

        }


        // return true;

    }



    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
