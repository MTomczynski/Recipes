package com.example.maciek.godt.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maciek.godt.R;
import com.example.maciek.godt.data.Image;
import com.example.maciek.godt.data.Ingredient;
import com.example.maciek.godt.data.Recipe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Recipe> recipes = new ArrayList<>();
    private List<Recipe> filtered = new ArrayList<>();

    @Inject
    public RecipesAdapter(Context context) {
        this.context = context;
    }

    public void setRecipes(@Nullable List<Recipe> recipes) {
        if(recipes == null) return;
        this.recipes.clear();
        this.recipes.addAll(recipes);
        this.filtered.addAll(recipes);
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
        Recipe recipe = filtered.get(position);
        holder.recipeTitle.setText(recipe.getTitle());
        List<Image> images = recipe.getImages();
        if(images.size() > 0)
            Glide.with(context).load(images.get(0).getUrl()).into(holder.recipeImage);
    }

    @Override
    public int getItemCount() {
        return filtered != null ? filtered.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                filtered.clear();
                if(constraint.length() == 0) {
                    filtered.addAll(recipes);
                } else {
                    filtered = getFilteredResults(constraint.toString().toLowerCase());
                }
                FilterResults results = new FilterResults();
                results.values = filtered;
                results.count = filtered.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filtered = (List<Recipe>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public List<Recipe> getFilteredResults(String constraint) {
        List<Recipe> results = new ArrayList<>();
        for(Recipe recipe : recipes) {
            boolean shouldAdd = false;
            for(Ingredient ingredient : recipe.getIngredients()) {
                if(ingredient.getName().toLowerCase().contains(constraint))
                    shouldAdd = true;
            }
            if(recipe.getTitle().toLowerCase().contains(constraint))
                shouldAdd = true;
            if(shouldAdd) {
                results.add(recipe);
            }
        }
        return results;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView recipeTitle;
        private ImageView recipeImage;

        ViewHolder(View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.recipe_title);
            recipeImage = itemView.findViewById(R.id.recipe_image);
        }
    }


}
