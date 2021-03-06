package com.galinc.hardtraining.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.galinc.hardtraining.MyApp;
import com.galinc.hardtraining.R;
import com.galinc.hardtraining.db.AppDatabase;
import com.galinc.hardtraining.itility.Document;
import com.galinc.hardtraining.recyclerview.DataAdapterTraining;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class TrainingActivity extends AppCompatActivity {
    long id;
    AppDatabase mDataBase = MyApp.getInstance().getDatabase();
    Disposable listTrainings;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_training);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.listlt);
        id = getIntent().getLongExtra("id",0L);
//        listTrainings = mDataBase.documentDao().getByIdFlow(id)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(document -> recyclerView.setAdapter(new DataAdapterTraining(getLayoutInflater(),document.listTrainings)));


        mDataBase.documentDao().getByIdLiveData(id).observe(this, document -> recyclerView.setAdapter(new DataAdapterTraining(getLayoutInflater(),document.listTrainings)));

//        Toast.makeText(this,mDataBase.documentDao().getByIdDoc(id).getDate(),Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.fablt);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mSwipeRefreshLayout.setRefreshing(false));


        }
}
