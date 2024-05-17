package com.example.projectschool;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface IngredientsDAO {
    @Query("SELECT * FROM ingredients")
    LiveData<List<Ingredients>> getAllIngredients();

    @Query("SELECT * FROM ingredients WHERE name = :name")
    LiveData<Ingredients> getIngredientsByName(String name);

    @Insert
    void insertIngredients(Ingredients ingredients);

    @Delete
    void deleteIngredients(Ingredients ingredients);

    @Update
    void updateIngredients(Ingredients ingredients);
}
