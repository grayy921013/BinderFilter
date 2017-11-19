package com.example.liuhaodong.myapplication;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuhaodong1 on 11/17/17.
 */

public class CaseListviewAdapter extends RecyclerView.Adapter<CaseListviewAdapter.ViewHolder> {

    List<Item> items;

    private class Item{
        Case aCase = null;
        boolean isSelected = false;
        boolean isSuccess = false;
    }

    @Inject
    public CaseListviewAdapter(List<Case> cases) {
        items = new ArrayList<>();
        for(Case c : cases){
            Item item = new Item();
            item.aCase = c;
            item.isSelected = false;
            items.add(item);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.info_id) TextView id;
        @BindView(R.id.info_name) TextView title;
        @BindView(R.id.info_description) TextView description;
        @BindView(R.id.info_select) CheckBox select;
        @BindView(R.id.info_top_layout) RelativeLayout topLayout;
        @BindView(R.id.info_expanded_layout) View expandedLayout;
        @BindView(R.id.info_data) TextView data;
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }

    @Override
    public CaseListviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item item = items.get(position);

        if(item.isSuccess){
            holder.topLayout.setBackgroundColor(Color.BLUE);
        }

        holder.topLayout.setOnClickListener(new View.OnClickListener() {

            final int pos = position;

            @Override
            public void onClick(View v) {
                if(holder.expandedLayout.getVisibility() == View.GONE) {
                    holder.expandedLayout.setVisibility(View.VISIBLE);
                    holder.data.setText(item.aCase.data());
                }else {
                    holder.expandedLayout.setVisibility(View.GONE);
                    holder.data.setText("");
                }
            }
        });
        holder.id.setText(String.valueOf(position));
        holder.title.setText(item.aCase.name());
        holder.description.setText(item.aCase.description());
        holder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.isSelected = isChecked;
            }
        });

    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Case> getSelectedCases(){
        List<Case> ret = new ArrayList<>();
        for(Item item : items){
            if(item.isSelected)
                ret.add(item.aCase);
        }
        return ret;
    }

    public void updateReceivedState(String title){
        for(Item item : items){
            if(item.aCase.name().equals(title)){
                item.isSuccess = true;
            }
        }
    }
}
