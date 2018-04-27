package com.example.maciek.godt;

import android.arch.persistence.room.OnConflictStrategy;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.maciek.godt.api.ApiClient;
import com.example.maciek.godt.api.ApiInterface;
import com.example.maciek.godt.model.Recipe;
import com.example.maciek.godt.model.RecipeResponse;

import java.util.List;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showRecipes();
    }

    private void showRecipes() {
        Observable<List<Recipe>> response = getRecipes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    Log.d("Da,", "IT should show recipes");
        DisposableObserver<List<Recipe>> disposableObserver = response.subscribeWith(new DisposableObserver<List<Recipe>>() {
            @Override
            public void onNext(List<Recipe> recipes) {
                Log.d("Result", String.valueOf(recipes.size()));
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("Query completed", "Completed");
            }
        });

        compositeDisposable.addAll(disposableObserver);
    }

    private Observable<List<Recipe>> getRecipes() {
        Retrofit retrofit = ApiClient.getClient();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        return apiInterface.getRecipes("0");
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
