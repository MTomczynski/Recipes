package com.example.maciek.godt.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.maciek.godt.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class RecipesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static final String TAG = "RECIPES ACTIVITY";

    @Inject
    public RecipesViewModelFactory recipesViewModelFactory;

    @Inject
    public RecipesAdapter adapter;

    public RecipesViewModel recipesViewModel;

    @BindView(R.id.recipes_recycler_view)
    RecyclerView recipesRecycler;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        setupViews();

        setupData();
    }

    private void setupViews() {
        recipesRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recipesRecycler.setLayoutManager(layoutManager);
        recipesRecycler.setAdapter(adapter);
    }

    private void setupData() {
        recipesViewModel = ViewModelProviders.of(this, recipesViewModelFactory)
            .get(RecipesViewModel.class);

        recipesViewModel.loadRecipes();

        recipesViewModel.recipesResult().observe(this,
                recipes -> adapter.setRecipes(recipes));

        recipesViewModel.recipesError().observe(this,
                error -> Log.e(TAG, error));
    }


    @Override
    public void onDestroy() {
        recipesViewModel.disposeElements();
        super.onDestroy();
    }

    @Override
    public boolean onQueryTextChange(String query) {
        // Here is where we are going to implement the filter logic
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
