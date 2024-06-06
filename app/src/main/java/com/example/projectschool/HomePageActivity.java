package com.example.projectschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectschool.DB.AppDatabase;

public class HomePageActivity extends AppCompatActivity {
    private static AppDatabase appDatabase;
    public TextView textView;


    public Button moveToEnterIngredients;
    public Button moveToEnterRecipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);


        moveToEnterIngredients= findViewById(R.id.EnterIng);
        moveToEnterRecipes= findViewById(R.id.enterRec);
        textView= findViewById(R.id.titleHomePade);





        moveToEnterIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent
                        (HomePageActivity.this,enterIngred.class);
                startActivity(intent);


            }
        });
       moveToEnterRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent
                        (HomePageActivity.this,CreateRecipeActivity.class);
                startActivity(intent);

            }
        });


    }


}