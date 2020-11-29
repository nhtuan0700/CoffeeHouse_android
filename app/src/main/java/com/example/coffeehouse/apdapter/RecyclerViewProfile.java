package com.example.coffeehouse.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeehouse.R;
import com.example.coffeehouse.model.News;
import com.example.coffeehouse.model.OptionProfile;

import java.util.List;

public class RecyclerViewProfile extends RecyclerView.Adapter<RecyclerViewProfile.MyViewHolder>  {
    private Context mContext;
    private List<OptionProfile> mData;
    private int resource;
    public RecyclerViewProfile(Context mContext, int resource, List<OptionProfile> mData) {
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
        holder.tvName.setText(mData.get(position).getName());
        holder.imageView.setImageResource(mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView imageView;
        LinearLayout btn;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
