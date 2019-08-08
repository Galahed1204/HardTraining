package com.galinc.hardtraining.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.galinc.hardtraining.R;
import com.galinc.hardtraining.itility.Document;
import com.galinc.hardtraining.net.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTrainings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trainings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            NetworkService
                    .getInstance()
                    .getJSONApi()
                    .postDocuments(NetworkService.GET_LISTOFTRAININGS)
                    .enqueue(new Callback<List<Document>>() {
                        @Override
                        public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                                List<Document> post = response.body();
                        }

                        @Override
                        public void onFailure(Call<List<Document>> call, Throwable t) {
                            Snackbar.make(view, t.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                        }
                    });
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
