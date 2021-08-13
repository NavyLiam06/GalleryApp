package com.example.galleryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.galleryapp.model.Item;

import java.text.SimpleDateFormat;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;
    protected ItemListener itemListener;

    public ItemAdapter(Context context, List<Item> items, ItemListener itemListener) {
        this.context = context;
        this.items = items;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        if(item.isHeader()){
            holder.headerHolder.setVisibility(View.VISIBLE);
            long date = item.getAddedDate() * 1000;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String dateText = format.format(date);
            holder.tvHeader.setText(dateText);
            holder.itemHolder.setVisibility(View.GONE);
        }else{
            holder.headerHolder.setVisibility(View.GONE);
            if(item.isVideo()){
                holder.videoTag.setVisibility(View.VISIBLE);
                long min = item.getDuration() / (1000 * 60);
                long sec = (item.getDuration() / 1000) % 60;
                String duration = (sec < 10) ? min+":0"+sec : min+":"+sec;
                holder.tvVideoDuration.setText(duration);
            }else{
                holder.videoTag.setVisibility(View.GONE);
            }
            String itemPath = item.getPath();
            Glide.with(context).load(itemPath).into(holder.imageView);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout headerHolder, videoTag;
        RelativeLayout itemHolder;
        TextView tvHeader, tvVideoDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headerHolder = itemView.findViewById(R.id.header_holder);
            tvHeader = itemView.findViewById(R.id.tv_header);
            itemHolder = itemView.findViewById(R.id.item_holder);
            imageView = itemView.findViewById(R.id.image_item);
            videoTag = itemView.findViewById(R.id.video_tag);
            tvVideoDuration = itemView.findViewById(R.id.video_duration);
        }
    }
    public interface ItemListener{
        void onItemClick(Item item);
    }
}
