package com.example.wallpaperapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRvAdapter extends RecyclerView.Adapter<CategoryRvAdapter.ViewHolder> implements CategoryClickLInterface {

    private Context context;
    private ArrayList<CategoryModal> modals;
    private CategoryClickLInterface categoryClickLInterface;


    public CategoryRvAdapter(Context context, ArrayList<CategoryModal> modals, CategoryClickLInterface categoryClickLInterface) {
        this.context = context;
        this.modals = modals;
        this.categoryClickLInterface = categoryClickLInterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_design_item, parent, false);

        return new CategoryRvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModal categoryModal = modals.get(position);
        holder.titleCategory.setText(categoryModal.getCategory());
        Picasso.get().load(categoryModal.getCategoryUrl()).into(holder.imageViewCategory);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                categoryClickLInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modals.size();
    }

    @Override
    public void onCategoryClick(int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleCategory;
        private ImageView imageViewCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleCategory = itemView.findViewById(R.id.titleCategory);
            imageViewCategory = itemView.findViewById(R.id.imageViewCategory);
        }

    }
}
