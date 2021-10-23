package com.example.dammetruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dammetruyen.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<String> lstImg;

    public RecyclerAdapter(List<String> lstImg) {
        this.lstImg = lstImg;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View recyclerView = inflater.inflate(R.layout.imganh, parent, false);

        // Return a new holder instance

        return new ViewHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        String urlImg = lstImg.get(position);

        // Set item views based on your views and data model
        holder.setImageView(urlImg);
    }


    @Override
    public int getItemCount() {
        return lstImg != null ? lstImg.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView imgAnhDoc;
        private Context mContext;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mContext = itemView.getContext();
            imgAnhDoc = itemView.findViewById(R.id.imgAnhDoc);


        }

        public void setImageView(String url) {
            Glide.with(mContext)
                    .load(url)
//                    .placeholder(R.drawable.placeholder)
//                    .error(R.drawable.imagenotfound)
                    .into(imgAnhDoc);
        }
    }
}
