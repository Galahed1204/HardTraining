package com.galinc.hardtraining;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        String str =  "gettrainings";
        String userCredentials = "guest:guest";
        final String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

        Post post1 = new Post();
        post1.setBody(str);

        textView = findViewById(R.id.main_text);
        NetworkService.getInstance()
                .getJSONApi()
                .postData(post1)
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        //Post post = response.body();
                        List<Post> posts = response.body();
//                        textView.append(post.getId() + "\n");
//                        textView.append(post.getUserId() + "\n");
//                        textView.append(post.getTitle() + "\n");
//                        textView.append(post.getBody() + "\n");

                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        textView.setText("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }
}
