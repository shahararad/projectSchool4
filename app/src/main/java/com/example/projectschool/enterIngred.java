package com.example.projectschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectschool.DB.AppDatabase;

public class enterIngred extends AppCompatActivity {
    private static AppDatabase appDatabase;
    public Button button1;
    public Button button2;

    public EditText entering;
    public EditText deleting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter_ingred);
        entering= findViewById(R.id.insertIngred);
        deleting= findViewById(R.id.deleteIngred);
        button1= findViewById(R.id.buttonEnter);

        button2= findViewById(R.id.DelButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp= entering.getText().toString();
                appDatabase = AppDatabase.getDatabase(enterIngred.this);
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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredientNameToDelete = deleting.getText().toString();
                appDatabase = AppDatabase.getDatabase(enterIngred.this);
                IngredientsDAO ingredientsDAO= appDatabase.ingredientsDAO();

                AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {

                        ingredientsDAO.deleteIngredientsByName(ingredientNameToDelete);
                    }
                });

            }
        });

    }

}