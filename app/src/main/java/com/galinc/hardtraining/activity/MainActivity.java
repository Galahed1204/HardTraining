package com.galinc.hardtraining.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.galinc.hardtraining.MyApp;
import com.galinc.hardtraining.R;
import com.galinc.hardtraining.activity.ListTrainings;
import com.galinc.hardtraining.db.AppDatabase;
import com.galinc.hardtraining.itility.Exercise;
import com.galinc.hardtraining.net.NetworkService;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDownload(View view){
        textView = findViewById(R.id.main_text);
        NetworkService.getInstance()
                .getJSONApi()
                .postData(NetworkService.GET_EXERCISES)
                .enqueue(new Callback<List<Exercise>>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                        List<Exercise> posts = response.body();
                        textView.setText(getResources().getText(R.string.app_name));

                        if (posts != null)
                        Observable.fromCallable(new CallableLongAction(posts))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(integer -> textView.append(integer.toString()));

                    }

                    @Override
                    public void onFailure(Call<List<Exercise>> call, Throwable t) {
                        textView.setText(getResources().getText(R.string.error_message));
                        t.printStackTrace();
                    }
                });
    }

    public void onClickGoToDocuments(View view) {
        Intent intent = new Intent(this, ListTrainings.class);
        startActivity(intent);

    }


    class CallableLongAction implements Callable<Integer> {

        private final  List<Exercise> data;

        private CallableLongAction( List<Exercise> data) {
            this.data = data;
        }

        @Override
        public Integer call() throws Exception {
            AppDatabase db = MyApp.getInstance().getDatabase();
            db.exerciseDao().deleteAll();
            db.exerciseDao().insert(data);
            return 0;
        }
    }
}
