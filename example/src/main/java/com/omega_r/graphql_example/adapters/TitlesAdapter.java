package com.omega_r.graphql_example.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omega_r.graphql_example.R;
import com.omega_r.graphql_example.model.title.Title;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class TitlesAdapter extends RecyclerView.Adapter<TitlesAdapter.ViewHolder> {

    private final List<Title> list = new ArrayList<>();

    public void setList(@Nullable List<Title> newList) {
        list.clear();
        if (newList != null) {
            list.addAll(newList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleLabel.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleLabel;

        ViewHolder(View itemView) {
            super(itemView);
            titleLabel = itemView.findViewById(R.id.label_title);
        }
    }
}
