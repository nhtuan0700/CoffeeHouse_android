package com.example.coffeehouse.apdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeehouse.R;
import com.example.coffeehouse.activity_detail_new;
import com.example.coffeehouse.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewNews extends RecyclerView.Adapter<RecyclerViewNews.MyViewHolder> {
    private Context mContext;
    private List<News> mData;
    private int resource;
    public RecyclerViewNews(Context mContext, int resource, List<News> mData) {
        this.resource = resource;
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(resource,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvDescription.setText(mData.get(position).getDescription());
        Picasso.get().load(mData.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvDescription;
        ImageView imageView;
        LinearLayout btn;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.image);
            tvDescription = itemView.findViewById(R.id.tv_description);
            btn = itemView.findViewById(R.id.btn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, activity_detail_new.class);
                    intent.putExtra("ID",mData.get(getAdapterPosition()).getId());
                    mContext.startActivity(intent);
                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, activity_detail_new.class);
                    intent.putExtra("ID",mData.get(getAdapterPosition()).getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
