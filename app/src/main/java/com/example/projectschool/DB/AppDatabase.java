package com.example.projectschool.DB;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;

import com.example.projectschool.Ingredients;
import com.example.projectschool.IngredientsDAO;
import com.example.projectschool.Recipe_Ingredients;
import com.example.projectschool.Recipe_IngredientsDAO;
import com.example.projectschool.Recipes;
import com.example.projectschool.RecipesDAO;
import com.example.projectschool.User;
import com.example.projectschool.UserDAO;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {User.class, Ingredients.class, Recipes.class, Recipe_Ingredients.class}, version = 4)



public abstract class AppDatabase extends RoomDatabase {

        private static final int Number_of_threads=4;

        public static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(Number_of_threads);

        public abstract UserDAO userDAO();
        public abstract IngredientsDAO ingredientsDAO();
        public abstract RecipesDAO recipesDAO();
        public abstract Recipe_IngredientsDAO recipeIngredientsDAO();

        private  static volatile AppDatabase INSTANCE;

        public static AppDatabase getDatabase(final Context context){
                if(INSTANCE == null){
                        synchronized (AppDatabase.class){
                                if(INSTANCE == null){
                                        INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"myDB").fallbackToDestructiveMigration().build();

                                }

                        }
                }return INSTANCE;
        }






}
