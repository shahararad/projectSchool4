package com.example.projectschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectschool.DB.AppDatabase;

public class enterRecpi extends AppCompatActivity {
    private static AppDatabase appDatabase;

    public Button button;
    public EditText enterName;
    public EditText enterInstructions;
    public EditText enterTheIngredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_recpi);

        button= findViewById(R.id.buttonRecipe);
        enterName= findViewById(R.id.EnterName);
        enterInstructions= findViewById(R.id.enterInstructions);
        enterTheIngredients=findViewById(R.id.entrerIngredientsOfRecipy);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String holderName= enterName.getText().toString();
                String holderInstructions= enterInstructions.getText().toString();
                String holderIngredientsList= enterTheIngredients.getText().toString();

                appDatabase = AppDatabase.getDatabase(enterRecpi.this);
                RecipesDAO recipesDAO= appDatabase.recipesDAO();
                IngredientsDAO ingredientsDAO= appDatabase.ingredientsDAO();
                Recipe_IngredientsDAO recipeIngredientsDAO= appDatabase.recipeIngredientsDAO();

                String[] productsArray = holderIngredientsList.split(",\\s*");// העברה של המוצרים למערך
                AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Recipes recipes= new Recipes(holderName,holderInstructions );
                        recipesDAO.insertRecipes(recipes);//שלב ראשון יצירת המתכון עם שם והוראות
                        for (String product : productsArray) {// שלב שני הכנסת המוצרים לטבלת המוצרים
                            Ingredients ingredient = new Ingredients(product);
                            ingredientsDAO.insertIngredients(ingredient);
                        }
                        int idOfRecipe= recipesDAO.getIdByName(holderName);//תז של המתכון
                        for(String product : productsArray){//שלב שלישי הכנסה לטבלה המשותפת
                            int IdOfIngredient= ingredientsDAO.getIdByName(product);//תז של מצרח מהמערך
                            Recipe_Ingredients recipeIngredients= new Recipe_Ingredients(idOfRecipe,IdOfIngredient," ");
                            recipeIngredientsDAO.insertRecipe_Ingredients(recipeIngredients);// הכנסה לטבלה המשותפת

                        }

                    }
                });


            }
        });
    }
}