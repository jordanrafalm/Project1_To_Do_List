package com.example.project1_to_do_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{
    private ArrayList<String> itemList;
    private Context context;
    private ImageView imageView;
    private OnItemListener onItemListener;
    private WebView webView;
    private List<String> mUrlList = Arrays.asList("https://wallbeing.com/2272-thickbox_default/grafika-joker.jpg", "https://safetyheads.com/wp-content/themes/safetyheads/images/logo.svg", "https://upload.wikimedia.org/wikipedia/commons/3/31/Most_Solidarno%C5%9Bci_w_P%C5%82ocku_4040.jpg","https://wallbeing.com/2272-thickbox_default/grafika-joker.jpg", "https://www.google.com", "https://upload.wikimedia.org/wikipedia/commons/3/31/Most_Solidarno%C5%9Bci_w_P%C5%82ocku_4040.jpg","https://wallbeing.com/2272-thickbox_default/grafika-joker.jpg", "https://www.google.com", "https://upload.wikimedia.org/wikipedia/commons/3/31/Most_Solidarno%C5%9Bci_w_P%C5%82ocku_4040.jpg","https://wallbeing.com/2272-thickbox_default/grafika-joker.jpg", "https://www.google.com", "https://upload.wikimedia.org/wikipedia/commons/3/31/Most_Solidarno%C5%9Bci_w_P%C5%82ocku_4040.jpg","https://wallbeing.com/2272-thickbox_default/grafika-joker.jpg", "https://www.google.com", "https://upload.wikimedia.org/wikipedia/commons/3/31/Most_Solidarno%C5%9Bci_w_P%C5%82ocku_4040.jpg");

    public RecyclerAdapter(ArrayList<String> itemList, Context context, ImageView imageView, OnItemListener onItemListener, WebView webView) {
        this.itemList = itemList;
        this.context = context;
        this.imageView = imageView;
        this.onItemListener = onItemListener;
        this.webView = webView;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);
        return new ItemViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.textViewItem.setText(itemList.get(position));

        }
            else {
            String url = mUrlList.get(position);
            holder.mWebView.loadUrl(url);
            holder.mWebView.setWebViewClient(new WebViewClient());
            }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textViewItem;
        private ImageView imageViewItem;
        public WebView mWebView;
        OnItemListener onItemListener;

        public ItemViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
           textViewItem = itemView.findViewById(R.id.textView2);
           imageViewItem = itemView.findViewById(R.id.imageView);
           mWebView = itemView.findViewById(R.id.webView);
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

