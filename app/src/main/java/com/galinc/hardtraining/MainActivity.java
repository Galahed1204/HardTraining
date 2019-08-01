package com.galinc.hardtraining;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.galinc.hardtraining.db.AppDatabase;
import com.galinc.hardtraining.itility.Exercise;
import com.galinc.hardtraining.itility.Post;
import com.galinc.hardtraining.net.NetworkService;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
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
        String str =  "gettrainings";
        Post post1 = new Post();
        post1.setBody(str);

        textView = findViewById(R.id.main_text);
        NetworkService.getInstance()
                .getJSONApi()
                .postData(post1)
                .enqueue(new Callback<List<Exercise>>() {
                    @Override
                    public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                        List<Exercise> posts = response.body();
                        textView.setText(getResources().getText(R.string.app_name));
                        Observable.fromCallable(new CallableLongAction(posts))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Integer>() {
                                    @Override
                                    public void accept(Integer integer) throws Exception {
                                        textView.append(integer.toString());
                                    }
                                });
                    }

                    @Override
                    public void onFailure(Call<List<Exercise>> call, Throwable t) {
                        textView.setText(getResources().getText(R.string.error_message));
                        t.printStackTrace();
                    }
                });
    }




    class CallableLongAction implements Callable<Integer> {

        private final  List<Exercise> data;

        public CallableLongAction( List<Exercise> data) {
            this.data = data;
        }

        @Override
        public Integer call() throws Exception {
            //return longAction(data);
            AppDatabase db = MyApp.getInstance().getDatabase();
            db.exerciseDao().deleteAll();
            db.exerciseDao().insert(data);
            return 0;
        }
    }
}
