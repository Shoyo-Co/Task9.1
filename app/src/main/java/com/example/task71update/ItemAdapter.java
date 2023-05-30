package com.example.task71update;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.postType.setText(item.getPostType());
        holder.name.setText(item.getName());
        holder.phone.setText(item.getPhone());
        holder.description.setText(item.getDescription());
        holder.date.setText(item.getDate());
        holder.location.setText(item.getLocation());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView postType, name, phone, description, date, location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postType = itemView.findViewById(R.id.postType);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            location = itemView.findViewById(R.id.location);
        }
    }
}

