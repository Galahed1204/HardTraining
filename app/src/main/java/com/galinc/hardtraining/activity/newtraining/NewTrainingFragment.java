package com.galinc.hardtraining.activity.newtraining;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.galinc.hardtraining.MyApp;
import com.galinc.hardtraining.R;
import com.galinc.hardtraining.db.AppDatabase;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewTrainingFragment extends Fragment {

    private NewTrainingViewModel mViewModel;
    private AppDatabase mDataBase = MyApp.getInstance().getDatabase();
    Disposable listDocuments;

    public static NewTrainingFragment newInstance() {
        return new NewTrainingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_training_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewTrainingViewModel.class);
        // TODO: Use the ViewModel
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);

        listDocuments = Completable.
                fromAction(() -> adapter.addAll(mDataBase.templateTrainingDao().getListName()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Выберете шаблон тренировки")
                            .setAdapter(adapter, (dialog, which) -> Toast.makeText(getContext(),String.valueOf(which),Toast.LENGTH_SHORT).show())
                            .setCancelable(false)
                            .setNegativeButton("Отмена",
                                    (dialog, id) -> dialog.cancel());
                    AlertDialog alert = builder.create();
                    alert.show();

                });
    }

    @Override
    public void onStop() {
        listDocuments.dispose();
        super.onStop();
    }
}
