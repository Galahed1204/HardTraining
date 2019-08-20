package com.galinc.hardtraining.recyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.galinc.hardtraining.R;
import com.galinc.hardtraining.activity.TrainingActivity;
import com.galinc.hardtraining.itility.Document;

import java.util.List;

public class DataAdapterDocument extends RecyclerView.Adapter<DataAdapterDocument.ViewHolder> {
    private LayoutInflater inflater;
    private List<Document> documents;
    private OnItemClicked listener;

    public DataAdapterDocument(LayoutInflater inflater, List<Document> documents) {
        this.inflater = inflater;
        this.documents = documents;
    }

    public DataAdapterDocument(LayoutInflater inflater, List<Document> documents, OnItemClicked listener) {
        this.inflater = inflater;
        this.documents = documents;
        this.listener = listener;
    }

    @Override
    public DataAdapterDocument.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(inflater.getContext()," " + viewType,Toast.LENGTH_LONG).show();
//
//            }
//        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapterDocument.ViewHolder holder, int position) {
        Document document = documents.get(position);
        holder.nameView.setText(document.getNumber());
        holder.dateView.setText(document.getDate());



        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(inflater.getContext()," " + position,Toast.LENGTH_LONG).show();
            Document document1 = documents.get(position);
            Intent intent = new Intent(holder.itemView.getContext(), TrainingActivity.class);

        });
        holder.nameView.setOnClickListener(
                v -> {
//                    Toast.makeText(inflater.getContext()," " + position,Toast.LENGTH_LONG).show();
                    long id = documents.get(position).id;
                    Intent intent = new Intent(inflater.getContext(), TrainingActivity.class);
                    intent.putExtra("id",id);
                    inflater.getContext().startActivity(intent);
                });

    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView, dateView;
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.image);
            nameView =  view.findViewById(R.id.name);
            dateView =  view.findViewById(R.id.date);
        }
    }

    public interface OnItemClicked {
        void onItemClick(View view, int position);
    }
}
