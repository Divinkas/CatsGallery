package com.example.divinkas.cats.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.divinkas.cats.Data.Hit;
import com.example.divinkas.cats.R;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    private List<Hit> hitList;
    private Context context;

    public GalleryAdapter(List<Hit> hitList, Context context) {
        this.hitList = hitList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return hitList.size();
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GalleryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_img, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        Glide.with(context)
                .load(hitList.get(position).getLargeImageURL())
                .asBitmap()
                .error(R.drawable.error_load)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(holder.items);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setNewListData(List<Hit> newList){
        hitList = newList;
        notifyDataSetChanged();
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder{
        ImageView items;

        GalleryViewHolder(View itemView) {
            super(itemView);
            items = itemView.findViewById(R.id.imgCats);
        }
    }

}
