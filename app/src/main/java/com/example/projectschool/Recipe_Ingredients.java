package com.example.projectschool;
import androidx.room.Entity;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity(tableName = "recipe_ingredients",
        primaryKeys = {"recipeId", "ingredientId"},
        foreignKeys = {
                @ForeignKey(entity = Recipes.class,
                        parentColumns = "id",
                        childColumns = "recipeId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Ingredients.class,
                        parentColumns = "id",
                        childColumns = "ingredientId",
                        onDelete = ForeignKey.CASCADE)
        })

public class Recipe_Ingredients {
        @NonNull
        private int recipeId;

        @NonNull
        private int ingredientId;

        @NonNull
        private String quantity;

        public Recipe_Ingredients(@NonNull int recipeId, @NonNull int ingredientId, String quantity){
                this.ingredientId= ingredientId;
                this.recipeId= recipeId;
                this.quantity= quantity;
        }



        public int getRecipeId() {
                return recipeId;
        }

        public void setRecipeId(int recipeId) {
                this.recipeId = recipeId;
        }

        public int getIngredientId() {
                return ingredientId;
        }

        public void setIngredientId(int ingredientId) {
                this.ingredientId = ingredientId;
        }

        public String getQuantity() {
                return quantity;
        }

        public void setQuantity(String quantity) {
                this.quantity = quantity;
        }
}
