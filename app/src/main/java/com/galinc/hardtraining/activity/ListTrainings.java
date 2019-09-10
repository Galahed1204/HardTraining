package com.galinc.hardtraining.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.galinc.hardtraining.MyApp;
import com.galinc.hardtraining.R;
import com.galinc.hardtraining.db.AppDatabase;
import com.galinc.hardtraining.itility.Document;
import com.galinc.hardtraining.itility.DocumentWithTrainings;
import com.galinc.hardtraining.itility.ListTraining;
import com.galinc.hardtraining.net.NetworkService;
import com.galinc.hardtraining.recyclerview.DataAdapterDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTrainings extends AppCompatActivity {

    List<Document> documents = new ArrayList<>();
    AppDatabase mDataBase = MyApp.getInstance().getDatabase();
    DocumentWithTrainings document;
    Disposable listDocuments;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trainings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.list);
        listDocuments = mDataBase.documentDao().getAllData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(documents -> {
                    this.documents = documents;
                    DataAdapterDocument adapter = new DataAdapterDocument(getLayoutInflater(), documents);
                    recyclerView.setAdapter(adapter);
                });


        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {

            NetworkService
                    .getInstance()
                    .getJSONApi()
                    .postDocuments(NetworkService.GET_LISTOFTRAININGS)
                    .enqueue(new Callback<List<Document>>() {
                        @Override
                        public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                            List<Document> post = response.body();
                            DataAdapterDocument adapter = new DataAdapterDocument(getLayoutInflater(), post);
                            recyclerView.setAdapter(adapter);

                            if (post != null){
                                Completable.fromAction(() -> {
                                    mDataBase.documentDao().deleteAll();
                                    mDataBase.documentDao().insert(post);

                                }).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe();

                            }
                            mSwipeRefreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void onFailure(Call<List<Document>> call, Throwable t) {
                            Snackbar.make(findViewById(R.id.swipe_container), t.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    });
            Toast.makeText(ListTrainings.this, "onRefresh", Toast.LENGTH_LONG).show();


        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {



//            fab.setEnabled(false);
//            //fab.setVisibility(FloatingActionButton.GONE);
//            NetworkService
//                    .getInstance()
//                    .getJSONApi()
//                    .postDocuments(NetworkService.GET_LISTOFTRAININGS)
//                    .enqueue(new Callback<List<Document>>() {
//                        @Override
//                        public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
//                            List<Document> post = response.body();
//                            DataAdapterDocument adapter = new DataAdapterDocument(getLayoutInflater(), post);
//                            recyclerView.setAdapter(adapter);
//
//                            if (post != null){
//                                Completable.fromAction(() -> {
//                                    mDataBase.documentDao().deleteAll();
//                                    mDataBase.documentDao().insert(post);
//
//                                }).subscribeOn(Schedulers.io())
//                                        .observeOn(AndroidSchedulers.mainThread())
//                                        .subscribe();
//
//                            }
//                            fab.setEnabled(true);
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<Document>> call, Throwable t) {
//                            Snackbar.make(view, t.toString(), Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                            fab.setEnabled(true);
//                        }
//                    });
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        listDocuments.dispose();
        super.onDestroy();
    }
}
