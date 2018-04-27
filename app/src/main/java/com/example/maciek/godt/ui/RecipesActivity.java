package com.example.maciek.godt.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.maciek.godt.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Provides;
import dagger.android.AndroidInjection;

public class RecipesActivity extends AppCompatActivity {

    @Inject
    public RecipesViewModelFactory recipesViewModelFactory;
    public RecipesViewModel recipesViewModel;

    @BindView(R.id.hello)
    TextView helloTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);
        recipesViewModel = ViewModelProviders.of(this, recipesViewModelFactory)
            .get(RecipesViewModel.class);

        recipesViewModel.loadRecipes();

        recipesViewModel.recipesResult().observe(this,
                recipes -> {
                    if (recipes != null) {
                        helloTv.setText("Hello: " + recipes.size());
                    } else {
                        helloTv.setText("Null response");
                    }
                });

        recipesViewModel.recipesError().observe(this,
                error -> {
                    helloTv.setText(error);
                });
    }

    @Override
    public void onDestroy() {
        recipesViewModel.disposeElements();
        super.onDestroy();
    }
}
