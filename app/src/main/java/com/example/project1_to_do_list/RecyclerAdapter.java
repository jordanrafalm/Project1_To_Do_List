package com.example.project1_to_do_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{
  private ArrayList<String> itemList;
  private Context context;
  private ImageView imageView;
  private  OnItemListener onItemListener;



    public RecyclerAdapter(ArrayList<String> itemList, Context context, ImageView imageView, OnItemListener onItemListener) {
        this.itemList = itemList;
        this.context = context;
        this.imageView = imageView;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);
       return new ItemViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
    holder.textViewItem.setText(itemList.get(position));


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textViewItem;
        private ImageView imageViewItem;
        OnItemListener onItemListener;

        public ItemViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
           textViewItem = itemView.findViewById(R.id.textView2);
            imageViewItem = itemView.findViewById(R.id.imageView);
            this.onItemListener = onItemListener;
              imageViewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }


}
