package com.example.maciek.godt.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maciek.godt.R;
import com.example.maciek.godt.data.Recipe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> recipes = new ArrayList<>();

    @Inject
    public RecipesAdapter(Context context) {
        this.context = context;
    }

    public void setRecipes(@Nullable List<Recipe> recipes) {
        if(recipes == null) return;
        this.recipes.clear();
        this.recipes.addAll(recipes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_view, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecipesAdapter.ViewHolder holder, int position) {
        holder.recipeTitle.setText(recipes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView recipeTitle;

        ViewHolder(View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.recipe_title);
        }
    }
}
