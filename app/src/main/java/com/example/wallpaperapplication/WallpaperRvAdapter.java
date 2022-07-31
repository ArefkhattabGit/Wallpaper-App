package com.example.wallpaperapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WallpaperRvAdapter extends RecyclerView.Adapter<WallpaperRvAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> arrayList;

    public WallpaperRvAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public WallpaperRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallpaper_design_item, parent, false);

        return new WallpaperRvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperRvAdapter.ViewHolder holder, int position) {

        Picasso.get().load(arrayList.get(position)).into(holder.imageViewWallpaper);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, WallpaperActivity.class);
                intent.putExtra("imageUrl", arrayList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewWallpaper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewWallpaper = itemView.findViewById(R.id.imageViewWallpaper);
        }
    }
}
