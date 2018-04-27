package com.example.maciek.godt.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.maciek.godt.data.Recipe;
import com.example.maciek.godt.data.source.RecipesRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RecipesViewModel extends ViewModel {

    private static final String TAG = "RECIPES VIEW MODEL";

    private final RecipesRepository recipesRepository;

    private MutableLiveData<List<Recipe>> recipesResult = new MutableLiveData<>();
    private MutableLiveData<String> recipesError = new MutableLiveData<>();
    private DisposableObserver<List<Recipe>> disposableObserver;

    @Inject
    public RecipesViewModel(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public LiveData<List<Recipe>> recipesResult() {
        return recipesResult;
    }

    public LiveData<String> recipesError() {
        return recipesError;
    }

    public void loadRecipes() {
        disposableObserver = new DisposableObserver<List<Recipe>>() {
            @Override
            public void onNext(List<Recipe> recipes) {
                recipesResult.postValue(recipes);
            }

            @Override
            public void onError(Throwable e) {
                recipesError.postValue(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Recipes loaded!");
            }
        };
        recipesRepository.getRecipes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(disposableObserver);
    }

    public void disposeElements() {
        if(disposableObserver != null && !disposableObserver.isDisposed())
            disposableObserver.dispose();
    }
}
