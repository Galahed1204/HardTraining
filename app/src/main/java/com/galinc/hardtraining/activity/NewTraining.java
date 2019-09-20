package com.galinc.hardtraining.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.galinc.hardtraining.R;
import com.galinc.hardtraining.activity.newtraining.NewTrainingFragment;

public class NewTraining extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_training_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NewTrainingFragment.newInstance())
                    .commitNow();
        }
    }
}
