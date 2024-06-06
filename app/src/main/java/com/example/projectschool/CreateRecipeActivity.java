package com.example.projectschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectschool.DB.AppDatabase;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.widget.Toast;

public class CreateRecipeActivity extends AppCompatActivity {
    private static AppDatabase appDatabase;
    private List<Ingredients> ingredientsList;


    private List<Ingredients> selectedIngredients = new ArrayList<>();
    private IngredientsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        appDatabase = AppDatabase.getDatabase(CreateRecipeActivity.this);
        RecipesDAO recipesDAO= appDatabase.recipesDAO();
        IngredientsDAO ingredientsDAO= appDatabase.ingredientsDAO();
        Recipe_IngredientsDAO recipeIngredientsDAO= appDatabase.recipeIngredientsDAO();

        // אתחול הרשימות
        ingredientsList = new ArrayList<>();
        selectedIngredients = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.ingredientsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IngredientsAdapter(ingredientsList, selectedIngredients);
        recyclerView.setAdapter(adapter);

        //List<Ingredients> ingredientsList = ingredientsDAO.getAllIngredients().getValue();


        ingredientsDAO.getAllIngredients().observe(this, new Observer<List<Ingredients>>() {
            @Override
            public void onChanged(List<Ingredients> ingredients) {
                if (ingredients != null) {
                    Log.d("CreateRecipeActivity", "Ingredients loaded: " + ingredients.size());
                    ingredientsList.clear();
                    ingredientsList.addAll(ingredients);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("CreateRecipeActivity", "Ingredients are null");
                }
            }
        });



        EditText recipeNameEditText = findViewById(R.id.recipeNameEditText);
        EditText instructionsEditText = findViewById(R.id.instructionsEditText);
        Button saveRecipeButton = findViewById(R.id.saveRecipeButton);

        saveRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipeName = recipeNameEditText.getText().toString();
                String instructions = instructionsEditText.getText().toString();

                if (recipeName.isEmpty() || selectedIngredients.isEmpty()) {

                    Toast.makeText(CreateRecipeActivity.this, "Please enter recipe name and select ingredients", Toast.LENGTH_SHORT).show();
                    return;
                }

                AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Recipes recipes= new Recipes(recipeName,instructions);
                        recipesDAO.insertRecipes(recipes);//שלב ראשון יצירת המתכון עם שם והוראות
                        int idOfRecipe= recipesDAO.getIdByName(recipeName);//תז של המתכון
                        for (Ingredients ingredient : selectedIngredients) {
                            int IdOfIngredient= ingredientsDAO.getIdByName(ingredient.getName());//תז של מצרך
                            Recipe_Ingredients recipeIngredients= new Recipe_Ingredients(idOfRecipe,IdOfIngredient," ");
                            recipeIngredientsDAO.insertRecipe_Ingredients(recipeIngredients);// הכנסה לטבלה המשותפת
                        }


                        // Toast.makeText(CreateRecipeActivity.this, "Recipe saved successfully", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}