package com.example.projectschool;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectschool.DB.AppDatabase;

public class HomePageActivity extends AppCompatActivity {
    private static AppDatabase appDatabase;

    public EditText text;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);

        text= findViewById(R.id.editTextText2);
        button= findViewById(R.id.justForNow);

        System.out.println("ביקהההההההההה להראות לאבאאאאאא");
        // test git
        // test 4
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp= text.getText().toString();
                appDatabase = AppDatabase.getDatabase(HomePageActivity.this);
                IngredientsDAO ingredientsDAO= appDatabase.ingredientsDAO();
                //LiveData<Ingredients> ing= ingredientsDAO.getIngredientsByName(temp);
                AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Ingredients ingredients= new Ingredients(temp);
                        ingredientsDAO.insertIngredients(ingredients);
                    }
                });

            }
        });


    }


}