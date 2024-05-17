package com.example.projectschool;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectschool.DB.AppDatabase;


public class SignUpFragment extends Fragment {
    private static AppDatabase appDatabase;
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
        Button button= view.findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String username = userName.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (username.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getActivity(), "אנא מלא את כל השדות", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.length() < 8) {
                    Toast.makeText(getActivity(), "הסיסמה לא נכונה(חייבת להיות 8 תווים לפחות)", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(username.length()<=2){
                    Toast.makeText(getActivity(), "שם משתמש מעל 2 תווים", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.matches(".*\\d.*") || !pass.matches(".*[a-z].*") || !pass.matches(".*[A-Z].*")) {
                    Toast.makeText(getActivity(), "הסיסמה חייבת לכלול לפחות אות אחת גדולה, אות אחת קטנה ומספר אחד", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(CheckToSignUp(username, pass)){



                    appDatabase = AppDatabase.getDatabase(getContext());
                    UserDAO userDAO= appDatabase.userDAO();
                    AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            User user= new User(username, pass);
                            userDAO.insertUser(user);

                        }
                    });
                    Toast.makeText(getActivity(), "מעולה רושמים אותך", Toast.LENGTH_SHORT).show();
                    System.out.println("עדדדדדדדדד הלוםםםםםםםם");
                    Intent intent = new Intent(getActivity(), HomePageActivity.class);
                    startActivity(intent);
                    getActivity().finish();





                }

            }
        });


        moveToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();
                welcomeActivity.changeFragmentToLogIn();
            }
        });
    }

    private boolean CheckToSignUp(String username, String password){

        appDatabase = AppDatabase.getDatabase(getContext());
        UserDAO userDao = appDatabase.userDAO();
        LiveData<User> checkUser= userDao.getUserByUsername(username);
        final boolean[] isAuthenticated = {false};

        checkUser.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user!= null){

                    return;
                }



            }
        });
        isAuthenticated[0]= true;



        return isAuthenticated[0];

    }
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


}