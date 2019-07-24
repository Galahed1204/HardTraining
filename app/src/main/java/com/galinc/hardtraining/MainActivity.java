package com.galinc.hardtraining;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.galinc.hardtraining.itility.Post;
import com.galinc.hardtraining.net.NetworkService;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        List<Post> posts = response.body();
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        textView.setText(getResources().getText(R.string.error_message));
                        t.printStackTrace();
                    }
                });
    }
}
