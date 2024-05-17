package com.example.projectschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView recipes;
    public TextView products;
    public TextView shoppingList;

    public TextView title;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent
                (MainActivity.this,WelcomeActivity.class);
        startActivity(intent);


        recipes= findViewById(R.id.recipes);
        products= findViewById(R.id.products);
        shoppingList= findViewById(R.id.shoppingList);
        title= findViewById(R.id.title);







         //database = AppDatabase.getDataBase(MainActivity.this);
        //ingredientDAO = database.ingredientDAO();




    }

}