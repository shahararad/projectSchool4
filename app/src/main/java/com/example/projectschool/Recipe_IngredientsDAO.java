package com.example.projectschool;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface Recipe_IngredientsDAO {
    @Query("SELECT * FROM Recipe_Ingredients")
    LiveData<List<Recipe_Ingredients>> getAllRecipesIngredients();

    @Query("SELECT * FROM Recipe_Ingredients WHERE recipeId = :recipeId")
    LiveData<Recipe_Ingredients> getRecipesIngredientsByName(int recipeId);

    @Insert
    void insertRecipe_Ingredients(Recipe_Ingredients recipeIngredients);

    @Delete
    void deleteRecipe_Ingredients(Recipe_Ingredients recipeIngredients);

    @Update
    void updateRecipe_Ingredients(Recipe_Ingredients recipeIngredients);
}
