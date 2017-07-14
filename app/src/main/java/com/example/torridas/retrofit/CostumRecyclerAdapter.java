package com.example.torridas.retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Torridas on 13-Jul-17.
 */

public class CostumRecyclerAdapter extends RecyclerView.Adapter< CostumRecyclerAdapter.CostumViewHolder> {

    private List<Result> results;
    public CostumRecyclerAdapter (List<Result> results){
        this.results = results;
    }

    @Override
    public CostumRecyclerAdapter.CostumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false );
        CostumViewHolder costumViewHolder =  new CostumViewHolder(view);
        return costumViewHolder;
    }

    @Override
    public void onBindViewHolder(CostumRecyclerAdapter.CostumViewHolder holder, int position) {
        Result result = results.get(position);
        holder.textViewId.setText(String.valueOf(result.getId()));
        holder.textViewName.setText(result.getName());
        holder.textViewUrl.setText(result.getUrl());
    }

    @Override
    public int getItemCount() {
        if( results != null ){
            return results.size();
        } else
            return 0;
    }

    public class CostumViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewId;
        private TextView textViewName;
        private TextView textViewUrl;

        public CostumViewHolder(View itemView) {
            super(itemView);
            textViewId  = (TextView)itemView.findViewById(R.id.resultID);
            textViewName = (TextView)itemView.findViewById(R.id.name);
            textViewUrl = (TextView) itemView.findViewById(R.id.url);

        }
    }
}
