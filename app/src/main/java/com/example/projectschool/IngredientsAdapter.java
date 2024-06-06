package com.example.projectschool;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private List<Ingredients> ingredientsList;
    private List<Ingredients> selectedIngredients;

    public IngredientsAdapter(List<Ingredients> ingredientsList, List<Ingredients> selectedIngredients) {
        this.ingredientsList = ingredientsList != null ? ingredientsList : new ArrayList<>();
        this.selectedIngredients = selectedIngredients != null ? selectedIngredients : new ArrayList<>();
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        Ingredients ingredient = ingredientsList.get(position);
        holder.ingredientNameTextView.setText(ingredient.getName());
        holder.checkBox.setChecked(selectedIngredients.contains(ingredient));

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedIngredients.add(ingredient);
            } else {
                selectedIngredients.remove(ingredient);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public static class IngredientsViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientNameTextView;
        CheckBox checkBox;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientNameTextView = itemView.findViewById(R.id.ingredientName);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}

