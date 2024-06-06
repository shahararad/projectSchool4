package com.example.projectschool;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao

public interface RecipesDAO {
    @Query("SELECT * FROM recipes")
    LiveData<List<Recipes>> getAllRecipes();

    @Query("SELECT * FROM recipes WHERE name = :name")
    LiveData<Recipes> getRecipeByName(String name);
    @Query("SELECT id FROM recipes WHERE name = :name")
    int getIdByName(String name); // - קבלת ה-Id לפי שם

    @Insert
    void insertRecipes(Recipes recipe);

    @Delete
    void deleteRecipes(Recipes recipe);

    @Update
    void updateRecipes(Recipes recipe);

}
