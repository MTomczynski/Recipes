package com.example.maciek.godt.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class RecipesViewModelFactory implements  ViewModelProvider.Factory {

    private RecipesViewModel recipesViewModel;


    @Inject
    public RecipesViewModelFactory(RecipesViewModel recipesViewModel) {
        this.recipesViewModel = recipesViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(RecipesViewModel.class)) {
            return (T) recipesViewModel;
        }
        throw new IllegalArgumentException("Unknown ViewModel class");

    }

}
